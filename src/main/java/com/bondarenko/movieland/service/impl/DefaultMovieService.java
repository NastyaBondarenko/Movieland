package com.bondarenko.movieland.service.impl;

import com.bondarenko.movieland.dto.MovieDto;
import com.bondarenko.movieland.entity.Genre;
import com.bondarenko.movieland.entity.Movie;
import com.bondarenko.movieland.mapper.MovieMapper;
import com.bondarenko.movieland.repository.MovieRepository;
import com.bondarenko.movieland.service.CurrencyService;
import com.bondarenko.movieland.service.EnrichmentService;
import com.bondarenko.movieland.service.GenreService;
import com.bondarenko.movieland.service.MovieService;
import com.bondarenko.movieland.service.dto.request.MovieDetailsDto;
import com.bondarenko.movieland.service.dto.request.MovieRequestDto;
import com.bondarenko.movieland.service.entity.common.CurrencyType;
import com.bondarenko.movieland.service.entity.request.MovieRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class DefaultMovieService implements MovieService {
    private final CurrencyService currencyService;
    private final GenreService genreService;
    private final MovieRepository movieRepository;
    private final EnrichmentService enrichmentService;
    private final MovieMapper movieMapper;

    @Override
    @Transactional(readOnly = true)
    public List<MovieDto> findAll(MovieRequest movieRequest) {
        if (movieRequest.getPrice() != null || movieRequest.getRating() != null) {
            return movieMapper.toMovieDtos(movieRepository.findAll(movieRequest));
        }
        return movieMapper.toMovieDtos(movieRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public List<MovieDto> findRandom() {
        List<Movie> randomMovies = movieRepository.findRandom();
        return movieMapper.toMovieDtos(randomMovies);
    }

    @Override
    @Transactional(readOnly = true)
    public MovieDetailsDto findById(int id, CurrencyType currencyType) {
        Movie enrichedMovie = movieRepository.findEnrichedMovieById(id);
        MovieDetailsDto movieDetailsDto = movieMapper.toMovieDetailsDto(enrichedMovie);
        convertPrice(currencyType, movieDetailsDto);
        return movieDetailsDto;
    }

    @Override
    @Transactional(readOnly = true)
    public List<MovieDto> findByGenre(MovieRequest movieRequest) {
        Integer genreId = movieRequest.getGenreId();
        if (movieRequest.getPrice() != null || movieRequest.getRating() != null) {
            return movieMapper.toMovieDtos(movieRepository.findByGenre(movieRequest, genreId));
        }
        return movieMapper.toMovieDtos(findMoviesByGenre(genreId));
    }

    @Override
    @Transactional
    public MovieDto add(MovieRequestDto movieRequestDto) {
        Movie movie = movieMapper.toMovie(movieRequestDto);
        enrichmentService.enrichMovieWithGenresAndCountries(movie, movieRequestDto);
        return movieMapper.toMovieDto(movieRepository.save(movie));
    }

    @Override
    @Transactional
    public MovieDto update(MovieRequestDto movieRequestDto, int movieId) {
        Movie enrichedMovie = movieRepository.findEnrichedMovieByCountriesAndGenres(movieId, movieRequestDto);
        movieMapper.update(enrichedMovie, movieRequestDto);
        return movieMapper.toMovieDto(movieRepository.save(enrichedMovie));
    }

    private List<Movie> findMoviesByGenre(int genreId) {
        Genre genre = genreService.findGenreById(genreId);
        return movieRepository.findMoviesByGenresIn(Set.of(genre));
    }

    private void convertPrice(CurrencyType currencyType, MovieDetailsDto movieDetailsDto) {
        if (currencyType != null) {
            double convertedPrice = currencyService.convertPrice(movieDetailsDto.getPrice(), currencyType);
            movieDetailsDto.setPrice(convertedPrice);
        }
    }
}