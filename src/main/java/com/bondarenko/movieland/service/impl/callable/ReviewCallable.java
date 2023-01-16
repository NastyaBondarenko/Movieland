package com.bondarenko.movieland.service.impl.callable;

import com.bondarenko.movieland.dto.ReviewDto;
import com.bondarenko.movieland.dto.TaskResultDto;
import com.bondarenko.movieland.service.ReviewService;
import com.bondarenko.movieland.service.dto.request.MovieDetailsDto;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;

@Slf4j

public class ReviewCallable implements Callable<MovieDetailsDto> {
    private static final long WAITING_TASK_TIME = 5000;//change
    MovieDetailsDto movieDetailsDto;
    private final ReviewService reviewService;
    private final int movieId;

    public ReviewCallable(ReviewService reviewService, int id, MovieDetailsDto movieDetailsDto) {
        this.reviewService = reviewService;
        this.movieId = id;
        this.movieDetailsDto = movieDetailsDto;

    }

    @Override
    public MovieDetailsDto call() throws Exception {
        log.info("[" + Thread.currentThread().getName() + "] call reviews " + reviewService.findByMovieId(movieId).size());
        movieDetailsDto.setReviews(reviewService.findByMovieId(movieId));
        return movieDetailsDto;
    }
}
