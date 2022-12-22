package com.bondarenko.movieland.service.impl;

import com.bondarenko.movieland.dto.MovieDto;
import com.bondarenko.movieland.entity.Genre;
import com.bondarenko.movieland.entity.Movie;
import com.bondarenko.movieland.exceptions.GenreNotFoundException;
import com.bondarenko.movieland.mapper.MovieMapper;
import com.bondarenko.movieland.repository.GenreRepository;
import com.bondarenko.movieland.repository.MovieRepository;
import com.bondarenko.movieland.service.MovieService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class DefaultMovieService implements MovieService {

    @Value("${movies.random.count:3}")
    private int randomMovieCount;
    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;
    private final MovieMapper movieMapper;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public List<MovieDto> findAll(Map<String, String> requestParameters) {
        List<Movie> movies = movieRepository.findAll();
        if (!requestParameters.isEmpty()) {
            return movieMapper.toMovieDtos(getSortedMovies(requestParameters));
        }
        return movieMapper.toMovieDtos(movies);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MovieDto> getRandom() {
        Pageable pageable = PageRequest.of(3, 6);
        List<Movie> randomMovies = new ArrayList<>(movieRepository.findAll(pageable).toList());
        Collections.shuffle(randomMovies);
        return movieMapper.toMovieDtos(randomMovies.subList(0, randomMovieCount));
    }

    @Override
    @Transactional(readOnly = true)
    public List<MovieDto> getByGenre(int genreId, Map<String, String> requestParameters) {
        Genre genre = genreRepository.findGenreById(genreId)
                .orElseThrow(() -> new GenreNotFoundException(genreId));
        List<Movie> moviesByGenre = movieRepository.findMoviesByGenreIn(Set.of(genre));

        if (!requestParameters.isEmpty()) {
            return movieMapper.toMovieDtos(getSortedMoviesByGenre(requestParameters, genreId));
        }
        return movieMapper.toMovieDtos(moviesByGenre);
    }

    private List<Movie> getSortedMovies(Map<String, String> queryParameters) {
        String sortColumn = queryParameters.keySet().stream().findFirst().get();
        String sortDirection = queryParameters.values().stream().findFirst().get();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Movie> query = builder.createQuery(Movie.class);
        Root<Movie> root = query.from(Movie.class);

        Path<Object> sortPath = root.get(sortColumn);
        Order order = sortDirection.equals("asc") ? builder.asc(sortPath) : builder.desc(sortPath);
        query.orderBy(order);

        return entityManager.createQuery(query).getResultList();
    }

    private List<Movie> getSortedMoviesByGenre(Map<String, String> queryParameters, int genreId) {
        String sortColumn = queryParameters.keySet().stream().findFirst().get();
        String sortDirection = queryParameters.values().stream().findFirst().get();


        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Movie> query = builder.createQuery(Movie.class);
        Root<Movie> root = query.from(Movie.class);

        Join<Movie, Genre> genre = root.join("genre");
        query.where(builder.equal(genre.get("id"), genreId));

        Path<Object> sortPath = root.get(sortColumn);
        Order order = sortDirection.equals("asc") ? builder.asc(sortPath) : builder.desc(sortPath);
        query.orderBy(order);

        return entityManager.createQuery(query).getResultList();
    }
}
