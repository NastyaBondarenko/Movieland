package com.bondarenko.movieland.service;

import com.bondarenko.movieland.dto.ReviewDto;
import com.bondarenko.movieland.service.dto.request.ReviewRequestDto;

import java.util.Set;

public interface ReviewService {
    void add(ReviewRequestDto reviewDto);

    Set<ReviewDto> findByMovieId(int id);
}