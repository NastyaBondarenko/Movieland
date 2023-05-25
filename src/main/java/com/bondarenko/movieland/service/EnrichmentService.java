package com.bondarenko.movieland.service;

import com.bondarenko.movieland.entity.Movie;
import com.bondarenko.movieland.service.dto.request.MovieRequestDto;

public interface EnrichmentService {
    Movie enrichMovie(Movie movie);

    Movie enrichMovieWithGenresAndCountries(Movie movie, MovieRequestDto movieDto);
}