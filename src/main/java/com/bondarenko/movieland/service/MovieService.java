package com.bondarenko.movieland.service;

import com.bondarenko.movieland.entity.Movie;

import java.util.List;

public interface MovieService {

    List<Movie> findAll();

    List<Movie> getRandomMovies();

    List<Movie> getByGenre(int genreId);
}
