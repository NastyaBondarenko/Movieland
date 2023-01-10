package com.bondarenko.movieland.service;

import com.bondarenko.movieland.dto.ReviewDto;
import com.bondarenko.movieland.service.dto.request.ReviewDtoShot;
import com.bondarenko.movieland.entity.Movie;

import java.util.Set;

public interface ReviewService {
    void add(ReviewDtoShot reviewDto);

    Set<ReviewDto> findByMovie(Movie movie);
}