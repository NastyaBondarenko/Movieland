package com.bondarenko.movieland.service;

import com.bondarenko.movieland.dto.MovieDetailsDto;
import com.bondarenko.movieland.dto.MovieDto;
import com.bondarenko.movieland.entity.MovieRequest;

import java.util.List;

public interface MovieService {

    List<MovieDto> findAll(MovieRequest movieRequest);

    List<MovieDto> getRandom();

    List<MovieDto> getByGenre(MovieRequest movieRequest);

    MovieDetailsDto getById(int movieId);
}