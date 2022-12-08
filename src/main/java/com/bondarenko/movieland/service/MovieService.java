package com.bondarenko.movieland.service;

import com.bondarenko.movieland.entity.Movie;

import java.util.List;
import java.util.Map;

public interface MovieService {

    List<Movie> findAll(Map<String, String> requestParameters);

    List<Movie> getRandomMovies();

    List<Movie> getByGenre(int genreId,Map<String, String> requestParameters);
}
