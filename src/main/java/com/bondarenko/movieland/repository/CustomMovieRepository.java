package com.bondarenko.movieland.repository;

import com.bondarenko.movieland.entity.Movie;
import com.bondarenko.movieland.service.entity.request.MovieRequest;

import java.util.List;

public interface CustomMovieRepository {
    List<Movie> findAll(MovieRequest movieRequest);

    List<Movie> findByGenre(MovieRequest movieRequest, Integer genreId);

    List<Movie> findRandom();
}