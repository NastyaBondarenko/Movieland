package com.bondarenko.movieland.service;

import com.bondarenko.movieland.dto.ReviewDto;
import com.bondarenko.movieland.entity.Review;
import com.bondarenko.movieland.service.dto.request.ReviewRequestDto;

import java.util.Set;

public interface ReviewService {
    ReviewDto add(ReviewRequestDto reviewDto);

    Set<Review> findByMovieId(int id);
}