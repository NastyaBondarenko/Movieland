package com.bondarenko.movieland.dao;

import com.bondarenko.movieland.entity.Movie;

import java.util.List;

public interface MovieDao {
    List<Movie> findAll();

    List<Movie> findByGenreId(int genreId);
}
