package com.bondarenko.movieland.service.impl;

import com.bondarenko.movieland.dto.MovieDto;
import com.bondarenko.movieland.entity.Movie;
import com.bondarenko.movieland.exceptions.MovieNotFoundException;
import com.bondarenko.movieland.mapper.MovieMapper;
import com.bondarenko.movieland.repository.MovieRepository;
import com.bondarenko.movieland.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DefaultMovieService implements MovieService {

    private static final int RANDOM_MOVIES_LENGTH = 3;
    private static final String RATING_PARAMETER = "rating";
    private static final String PRICE_PARAMETER = "price";
    private static final String ASC_PARAMETER = "asc";
    private static final String DESC_PARAMETER = "desc";

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    @Override
    @Transactional(readOnly = true)
    public List<MovieDto> findAll(Map<String, String> requestParameters) {
        List<Movie> movies = movieRepository.findAll();
        if (!requestParameters.isEmpty()) {
            return movieMapper.moviesToMovieDtos(getSortedMovies(requestParameters, movies));
        }
        return movieMapper.moviesToMovieDtos(movies);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MovieDto> getRandomMovies() {
        Pageable pageable = PageRequest.of(3, 6);
        List<Movie> movies = new ArrayList<>(movieRepository.findAll(pageable).getContent());
        Collections.shuffle(movies);
        return movieMapper.moviesToMovieDtos(movies.subList(0, RANDOM_MOVIES_LENGTH));
    }

    @Override
    @Transactional(readOnly = true)
    public List<MovieDto> getByGenre(int genreId, Map<String, String> requestParameters) {
        List<Movie> moviesByGenre = movieRepository.findMovieByGenreId(genreId);
        if (!requestParameters.isEmpty()) {
            return movieMapper.moviesToMovieDtos(getSortedMovies(requestParameters, moviesByGenre));
        }
        return movieMapper.moviesToMovieDtos(moviesByGenre);
    }

    private List<Movie> getSortedMovies(Map<String, String> queryParameters, List<Movie> movies) {
        String firstParameter = queryParameters.keySet().stream().findFirst().get();
        String secondParameter = queryParameters.values().stream().findFirst().get();

        if (firstParameter.equals(RATING_PARAMETER)) {
            return getByRating(movies, secondParameter);
        }
        if (firstParameter.equals(PRICE_PARAMETER)) {
            return getByPrice(movies, secondParameter);
        }
        throw new MovieNotFoundException(firstParameter);
    }

    private List<Movie> getByRating(List<Movie> movies, String requestParameter) {
        if (requestParameter.equals(DESC_PARAMETER)) {
            return movies.stream()
                    .sorted(Comparator.comparing(Movie::getRating).reversed())
                    .toList();
        }
        throw new MovieNotFoundException(requestParameter);
    }

    private List<Movie> getByPrice(List<Movie> movies, String requestParameter) {
        if (requestParameter.equals(DESC_PARAMETER)) {
            return movies.stream()
                    .sorted(Comparator.comparing(Movie::getPrice).reversed())
                    .toList();
        }
        if (requestParameter.equals(ASC_PARAMETER)) {
            return movies.stream()
                    .sorted(Comparator.comparing(Movie::getPrice).reversed().reversed()).toList();
        }
        throw new MovieNotFoundException(requestParameter);
    }
}