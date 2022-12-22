package com.bondarenko.movieland.service.impl;

import com.bondarenko.movieland.dto.MovieDto;
import com.bondarenko.movieland.entity.Genre;
import com.bondarenko.movieland.entity.Movie;
import com.bondarenko.movieland.entity.MovieRequest;
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
    public List<MovieDto> findAll(MovieRequest movieRequest) {
        List<Movie> movies = movieRepository.findAll();
        if (movieRequest != null) {
            return movieMapper.toMovieDtos(getSortedMovies(movieRequest));
        }
        return movieMapper.toMovieDtos(movies);
    }

    private List<Movie> getSortedMovies(MovieRequest movieRequest) {
        String sortDirection = null;
        String sortColumn = null;

        String price = movieRequest.getPrice();
        String rating = movieRequest.getRating();
        if (movieRequest.getPrice() != null) {
            sortDirection = price;
            sortColumn = "price";

        } else {
            sortDirection = "rating";
            sortColumn = rating;
        }
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Movie> query = builder.createQuery(Movie.class);
        Root<Movie> root = query.from(Movie.class);

        Path<Object> sortPath = root.get(sortColumn);
        Order order = sortDirection.equals("asc") ? builder.asc(sortPath) : builder.desc(sortPath);
        query.orderBy(order);

        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public List<MovieDto> findAll(String first, String second) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<MovieDto> getRandom() {
        Pageable pageable = PageRequest.of(3, 6);
        List<Movie> randomMovies = new ArrayList<>(movieRepository.findAll(pageable).toList());
        Collections.shuffle(randomMovies);
        return movieMapper.toMovieDtos(randomMovies.subList(0, randomMovieCount));
    }

//    @Override
//    @Transactional(readOnly = true)
//    public List<MovieDto> getByGenre(int genreId, String sortColumn, String sortDirection) {
//        List<Movie> moviesByGenre = getMoviesByGenre(genreId);
//
//        if (!sortColumn.isEmpty()) {
//            return movieMapper.toMovieDtos(getSortedMoviesByGenre(sortColumn, sortDirection, genreId));
//        }
//        return movieMapper.toMovieDtos(moviesByGenre);
//    }


    private List<Movie> getSortedMovies(String sortColumn, String sortDirection) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Movie> query = builder.createQuery(Movie.class);
        Root<Movie> root = query.from(Movie.class);

        Path<Object> sortPath = root.get(sortColumn);
        Order order = sortDirection.equals("asc") ? builder.asc(sortPath) : builder.desc(sortPath);
        query.orderBy(order);

        return entityManager.createQuery(query).getResultList();
    }

    private List<Movie> getSortedMoviesByGenre(String sortColumn, String sortDirection, int genreId) {
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

    private List<Movie> getMoviesByGenre(int genreId) {
        Genre genre = genreRepository.findGenreById(genreId)
                .orElseThrow(() -> new GenreNotFoundException(genreId));
        return movieRepository.findMoviesByGenreIn(Set.of(genre));
    }
}
