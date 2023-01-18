package com.bondarenko.movieland.service.enrichment;

import com.bondarenko.movieland.dto.CountryDto;
import com.bondarenko.movieland.dto.GenreDto;
import com.bondarenko.movieland.dto.ReviewDto;
import com.bondarenko.movieland.service.CountryService;
import com.bondarenko.movieland.service.FutureService;
import com.bondarenko.movieland.service.GenreService;
import com.bondarenko.movieland.service.ReviewService;
import com.bondarenko.movieland.service.entity.common.TaskResult;
import com.google.common.annotations.VisibleForTesting;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

@Slf4j
@AllArgsConstructor
@Service
public class CallableFutureService implements FutureService {
    private final CountryService countryService;
    private final GenreService genreService;
    private final ReviewService reviewService;
    private ExecutorService executorService;

    @Override
    public List<Future<TaskResult>> getFuturesList(int id, TaskResult taskResult) {
        Future<TaskResult> futureCountryDtos = getFutureCountryDtos(id, taskResult);
        Future<TaskResult> futureReviewDtos = getFutureReviewDtos(id, taskResult);
        Future<TaskResult> futureGenreDtos = getFutureGenreDtos(id, taskResult);
        return List.of(futureCountryDtos, futureReviewDtos, futureGenreDtos);
    }

    @VisibleForTesting
    Future<TaskResult> getFutureCountryDtos(int movieId, TaskResult taskResult) {
        Set<CountryDto> countryDto = countryService.findByMovieId(movieId);
        taskResult.setCountryDtos(countryDto);
        return CompletableFuture.supplyAsync(() -> taskResult, executorService);
    }

    @VisibleForTesting
    Future<TaskResult> getFutureGenreDtos(int movieId, TaskResult taskResult) {
        Set<GenreDto> genreDtos = genreService.findByMovieId(movieId);
        taskResult.setGenreDtos(genreDtos);
        return CompletableFuture.supplyAsync(() -> taskResult, executorService);
    }

    @VisibleForTesting
    Future<TaskResult> getFutureReviewDtos(int movieId, TaskResult taskResult) {
        Set<ReviewDto> reviewDtos = reviewService.findByMovieId(movieId);
        taskResult.setReviewDtos(reviewDtos);
        return CompletableFuture.supplyAsync(() -> taskResult, executorService);
    }
}