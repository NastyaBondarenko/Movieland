package com.bondarenko.movieland.service.impl;

import com.bondarenko.movieland.dto.MovieDto;
import com.bondarenko.movieland.entity.Movie;
import com.bondarenko.movieland.exceptions.MovieNotFoundException;
import com.bondarenko.movieland.mapper.MovieMapper;
import com.bondarenko.movieland.repository.MovieRepository;
import com.bondarenko.movieland.service.CurrencyService;
import com.bondarenko.movieland.service.EnrichmentService;
import com.bondarenko.movieland.service.MovieService;
import com.bondarenko.movieland.service.dto.request.MovieDetailsDto;
import com.bondarenko.movieland.service.dto.request.MovieRequestDto;
import com.bondarenko.movieland.service.entity.common.CurrencyType;
import com.bondarenko.movieland.service.entity.request.MovieRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.ref.SoftReference;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Primary
@Service
@RequiredArgsConstructor
public class EnrichedMovieCache implements MovieService {
    private final Map<Integer, SoftReference<Movie>> cachedMovieMap = new ConcurrentHashMap<>();
    private final EnrichmentService enrichmentService;
    private final CurrencyService currencyService;
    private final MovieRepository movieRepository;
    private final MovieService movieService;
    private final MovieMapper movieMapper;

    @Override
    @Transactional(readOnly = true)
    public MovieDetailsDto findById(int movieId, CurrencyType currencyType) {
        Movie enrichedMovie = findEnrichedMovie(movieId);
        MovieDetailsDto movieDetailsDto = movieMapper.toMovieDetailsDto(enrichedMovie);
        convertPrice(currencyType, movieDetailsDto);
        return movieDetailsDto;
    }

    @Override
    @Transactional(readOnly = true)
    public List<MovieDto> findAll(MovieRequest movieRequest) {
        return movieService.findAll(movieRequest);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MovieDto> findRandom() {
        return movieService.findRandom();
    }

    @Override
    @Transactional(readOnly = true)
    public List<MovieDto> findByGenre(MovieRequest movieRequest) {
        return movieService.findByGenre(movieRequest);
    }

    @Override
    @Transactional(readOnly = true)
    public Movie findById(int movieId) {
        return movieService.findById(movieId);
    }

    @Override
    public MovieDto add(MovieRequestDto movieDetailsDto) {
        return movieService.add(movieDetailsDto);
    }

    @Override
    public MovieDto update(MovieRequestDto movieRequestDto, int movieId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new MovieNotFoundException(movieId));
        Movie updatedMovie = movieMapper.update(movie, movieRequestDto);
        Movie enrichedMovie = enrichMovieWithCountriesAndGenres(updatedMovie, movieRequestDto);
        return movieMapper.toMovieDto(movieRepository.save(enrichedMovie));
    }

    private Movie findEnrichedMovie(int movieId) {
        SoftReference<Movie> movieSoftReference = cachedMovieMap.compute(movieId, (key, value) -> {
            if (value != null && value.get() != null) {
                log.info("Get enriched movie from cache by movieId={} ", movieId);
                return value;
            }
            log.info("Enrich cache with movie by movieId={} ", movieId);
            return new SoftReference<>(enrichMovie(movieId));
        });
        return movieSoftReference.get();
    }

    private Movie enrichMovieWithCountriesAndGenres(Movie movie, MovieRequestDto movieRequestDto) {
        int movieId = movie.getId();
        SoftReference<Movie> movieSoftReference = cachedMovieMap.compute(movieId, (key, val) ->
                new SoftReference<>(enrichMovieByGenresAndCountries(movie, movieRequestDto)));
        return movieSoftReference.get();
    }

    private Movie enrichMovie(int movieId) {
        Movie movie = movieService.findById(movieId);
        return enrichmentService.enrichMovie(movie);
    }

    private Movie enrichMovieByGenresAndCountries(Movie movie, MovieRequestDto movieRequestDto) {
        return enrichmentService.enrichMovieWithGenresAndCountries(movie, movieRequestDto);
    }

    private void convertPrice(CurrencyType currencyType, MovieDetailsDto movieDetailsDto) {
        if (currencyType != null) {
            double convertedPrice = currencyService.convertPrice(movieDetailsDto.getPrice(), currencyType);
            movieDetailsDto.setPrice(convertedPrice);
        }
    }
}