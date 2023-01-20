package com.bondarenko.movieland.service;

import com.bondarenko.movieland.service.dto.request.MovieDetailsDto;

public interface EnrichmentService {
    MovieDetailsDto enrichMovieDetailsDto(MovieDetailsDto movieDetailsDto);
}