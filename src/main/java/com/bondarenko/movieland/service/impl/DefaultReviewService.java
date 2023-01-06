package com.bondarenko.movieland.service.impl;

import com.bondarenko.movieland.dto.ReviewDto;
import com.bondarenko.movieland.entity.Movie;
import com.bondarenko.movieland.mapper.ReviewMapper;
import com.bondarenko.movieland.repository.ReviewRepository;
import com.bondarenko.movieland.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class DefaultReviewService implements ReviewService {
    private final ReviewRepository reviewRepository;
    @Autowired
    private ReviewMapper reviewMapper;

    @Override
    @Transactional(readOnly = true)
    public Set<ReviewDto> findByMovie(Movie movie) {
        return reviewMapper.toReviewDtos(reviewRepository.findByMovie(movie));
    }
}