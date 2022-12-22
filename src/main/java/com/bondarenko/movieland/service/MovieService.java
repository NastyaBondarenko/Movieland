package com.bondarenko.movieland.service;

import com.bondarenko.movieland.dto.MovieDto;
import com.bondarenko.movieland.entity.MovieRequest;

import java.util.List;

public interface MovieService {

    List<MovieDto> findAll(String first, String second);
//    List<MovieDto> findAll(MovieRequest movieRequest);

    List<MovieDto> getRandom();

//    List<MovieDto> getByGenre(int genreId, String first, String second);

    List<MovieDto> findAll(MovieRequest movieRequest);
}
