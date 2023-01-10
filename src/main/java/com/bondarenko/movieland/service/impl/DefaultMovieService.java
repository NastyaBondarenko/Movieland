package com.bondarenko.movieland.service.impl;

import com.bondarenko.movieland.service.dto.request.MovieDetailsDto;
import com.bondarenko.movieland.dto.MovieDto;
import com.bondarenko.movieland.service.dto.request.MovieRequestDto;
import com.bondarenko.movieland.dto.ReviewDto;
import com.bondarenko.movieland.entity.Country;
import com.bondarenko.movieland.service.entity.common.CurrencyType;
import com.bondarenko.movieland.entity.Genre;
import com.bondarenko.movieland.entity.Movie;
import com.bondarenko.movieland.service.entity.request.MovieRequest;
import com.bondarenko.movieland.exceptions.MovieNotFoundException;
import com.bondarenko.movieland.mapper.MovieMapper;
import com.bondarenko.movieland.repository.MovieRepository;
import com.bondarenko.movieland.service.CountryService;
import com.bondarenko.movieland.service.CurrencyService;
import com.bondarenko.movieland.service.GenreService;
import com.bondarenko.movieland.service.MovieService;
import com.bondarenko.movieland.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class DefaultMovieService implements MovieService {
    private final CurrencyService currencyService;
    private final CountryService countryService;
    private final ReviewService reviewService;
    private final GenreService genreService;
    private final MovieRepository movieRepository;
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
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new MovieNotFoundException(id));
        MovieDetailsDto movieDetailsDto = movieMapper.toMovieDetailsDto(movie);
        Set<ReviewDto> reviewDtos = reviewService.findByMovieId(id);
        movieDetailsDto.setReviews(reviewDtos);
        if (currencyType != null) {
            double convertedPrice = currencyService.convertPrice(movieDetailsDto.getPrice(), currencyType);
            movieDetailsDto.setPrice(convertedPrice);
        }
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
    public void add(MovieRequestDto movieRequestDto) {
        Movie movie = movieMapper.toMovie(movieRequestDto);
        enrichMovie(movie, movieRequestDto);
        movieRepository.save(movie);
    }

    @Override
    @Transactional
    public void update(MovieRequestDto movieRequestDto, int movieId) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new MovieNotFoundException(movieId));
        enrichMovie(movie, movieRequestDto);
        movieMapper.update(movie, movieRequestDto);
        movieRepository.save(movie);
    }

    private void enrichMovie(Movie movie, MovieRequestDto movieDto) {
        Set<Genre> genres = genreService.findByIdIn(movieDto.getGenreIds());
        Set<Country> countries = countryService.findByIdIn(movieDto.getCountryIds());
        movie.setGenres(genres);
        movie.setCountries(countries);
    }

    private List<Movie> findMoviesByGenre(int genreId) {
        Genre genre = genreService.findGenreById(genreId);
        return movieRepository.findMoviesByGenresIn(Set.of(genre));
    }
}