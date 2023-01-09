package com.bondarenko.movieland.repository;

import com.bondarenko.movieland.entity.Movie;
import com.bondarenko.movieland.entity.MovieRequest;

import java.util.List;

public interface CustomMovieRepository {
    List<Movie> getAll(MovieRequest movieRequest);

    List<Movie> getByGenre(MovieRequest movieRequest, Integer genreId);

    List<Movie> getRandom();
}