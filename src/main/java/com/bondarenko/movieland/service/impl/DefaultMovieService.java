package com.bondarenko.movieland.service.impl;

import com.bondarenko.movieland.dto.MovieDto;
import com.bondarenko.movieland.entity.Genre;
import com.bondarenko.movieland.entity.Movie;
import com.bondarenko.movieland.entity.SortDirection;
import com.bondarenko.movieland.exceptions.GenreNotFoundException;
import com.bondarenko.movieland.exceptions.MovieNotFoundException;
import com.bondarenko.movieland.mapper.MovieMapper;
import com.bondarenko.movieland.repository.GenreRepository;
import com.bondarenko.movieland.repository.MovieRepository;
import com.bondarenko.movieland.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class DefaultMovieService implements MovieService {

    @Value("${movies.random.count:3}")
    private int intRandomNumber;
    private static final String RATING_PARAMETER = "rating";
    private static final String PRICE_PARAMETER = "price";
    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;
    private final MovieMapper movieMapper;

    @Override
    @Transactional(readOnly = true)
    public List<MovieDto> findAll(Map<String, String> requestParameters) {
        List<Movie> movies = movieRepository.findAll();
        if (!requestParameters.isEmpty()) {
            return movieMapper.toMovieDtos(getSortedMovies(requestParameters, movies));
        }
        return movieMapper.toMovieDtos(movies);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MovieDto> getRandom() {
        Pageable pageable = PageRequest.of(3, 6);
        List<Movie> randomMovies = new ArrayList<>(movieRepository.findAll(pageable).toList());
        Collections.shuffle(randomMovies);
        return movieMapper.toMovieDtos(randomMovies.subList(0, intRandomNumber));
    }

    @Override
    @Transactional(readOnly = true)
    public List<MovieDto> getByGenre(int genreId, Map<String, String> requestParameters) {
        Genre genre = genreRepository.findGenreById(genreId)
                .orElseThrow(() -> new GenreNotFoundException(genreId));
        return movieMapper.toMovieDtos(movieRepository.findMoviesByGenreIn(Set.of(genre)));
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
        if (requestParameter.equals(SortDirection.DESC.toString())) {
            return movies.stream()
                    .sorted(Comparator.comparing(Movie::getRating).reversed())
                    .toList();
        }
        throw new MovieNotFoundException(requestParameter);
    }

    private List<Movie> getByPrice(List<Movie> movies, String requestParameter) {
        if (requestParameter.equals(SortDirection.DESC.toString())) {
            return movies.stream()
                    .sorted(Comparator.comparing(Movie::getPrice).reversed())
                    .toList();
        }
        if (requestParameter.equals(SortDirection.ASC.toString())) {
            return movies.stream()
                    .sorted(Comparator.comparing(Movie::getPrice).reversed().reversed()).toList();
        }
        throw new MovieNotFoundException(requestParameter);
    }
}