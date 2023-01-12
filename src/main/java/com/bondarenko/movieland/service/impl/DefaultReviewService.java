package com.bondarenko.movieland.service.impl;

import com.bondarenko.movieland.dto.ReviewDto;
import com.bondarenko.movieland.exceptions.IllegalReviewForOperation;
import com.bondarenko.movieland.service.dto.request.ReviewRequestDto;
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
    public ReviewDto add(ReviewRequestDto reviewDto) {
        Review review = reviewMapper.toReview(reviewDto);
        if (checkIfNew(review)) {
            return reviewMapper.toReviewDto(reviewRepository.save(review));
        }
        throw new IllegalReviewForOperation(reviewDto.getDescription());
    }

    @Override
    @Transactional(readOnly = true)
    public Set<ReviewDto> findByMovieId(int id) {
        return reviewMapper.toReviewDtos(reviewRepository.findByMovieId(id));
    }

    private boolean checkIfNew(Review newReview) {
        Movie movie = newReview.getMovie();
        String description = newReview.getDescription();
        Optional<Review> review = reviewRepository.findByMovieAndDescription(movie, description);
        return review.isEmpty();
    }
}