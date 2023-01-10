package com.bondarenko.movieland.service.impl;

import com.bondarenko.movieland.dto.ReviewDto;
import com.bondarenko.movieland.service.dto.request.ReviewDtoShot;
import com.bondarenko.movieland.entity.Movie;
import com.bondarenko.movieland.entity.Review;
import com.bondarenko.movieland.mapper.ReviewMapper;
import com.bondarenko.movieland.repository.ReviewRepository;
import com.bondarenko.movieland.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class DefaultReviewService implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    @Override
    @Transactional
    public void add(ReviewDtoShot reviewDto) {
        Review review = reviewMapper.toReview(reviewDto);
        if (checkIfNew(review)) {
            reviewRepository.save(review);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Set<ReviewDto> findByMovie(Movie movie) {
        return reviewMapper.toReviewDtos(reviewRepository.findByMovie(movie));
    }

    private boolean checkIfNew(Review newReview) {
        Movie movie = newReview.getMovie();
        String description = newReview.getDescription();
        Optional<Review> review = reviewRepository.findByMovieAndDescription(movie, description);
        return review.isEmpty();
    }
}