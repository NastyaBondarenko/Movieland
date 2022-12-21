package com.bondarenko.movieland.service;

import com.bondarenko.movieland.dto.MovieDto;

import java.util.List;
import java.util.Map;

public interface MovieService {

    List<MovieDto> findAll(Map<String, String> requestParameters);

    List<MovieDto> getRandom();

    List<MovieDto> getByGenre(int genreId, Map<String, String> requestParameters);
}
