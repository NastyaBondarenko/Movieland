package com.bondarenko.movieland.service;

import com.bondarenko.movieland.dto.MovieDto;
import com.bondarenko.movieland.entity.Movie;
import com.bondarenko.movieland.entity.MovieRequest;

import java.util.List;

public interface MovieService {

    List<MovieDto> findAll(MovieRequest movieRequest);

    List<MovieDto> getRandom();

    List<MovieDto> getByGenre(MovieRequest movieRequest);

    Movie getById(int movieId);
}
