package com.bondarenko.movieland.service.impl;

import com.bondarenko.movieland.dto.ReviewDto;
import com.bondarenko.movieland.service.ReviewService;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;
import java.util.concurrent.Callable;

@Slf4j

public class ReviewCallable implements Callable<Set<ReviewDto>> {
    private static final long WAITING_TASK_TIME = 5000;//change

    private final ReviewService reviewService;
    private final int movieId;

    public ReviewCallable(ReviewService reviewService, int id) {
        this.reviewService = reviewService;
        this.movieId = id;
    }

    @Override
    public Set<ReviewDto> call() throws Exception {
        log.info("[" + Thread.currentThread().getName() + "] FINDBY " + "call reviews");
        return reviewService.findByMovieId(movieId);
    }
}
