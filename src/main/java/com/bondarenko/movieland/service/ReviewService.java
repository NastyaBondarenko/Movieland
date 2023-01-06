package com.bondarenko.movieland.service;

import com.bondarenko.movieland.dto.ReviewDto;
import com.bondarenko.movieland.entity.Movie;

import java.util.Set;

public interface ReviewService {
    Set<ReviewDto> findByMovie(Movie movie);
}