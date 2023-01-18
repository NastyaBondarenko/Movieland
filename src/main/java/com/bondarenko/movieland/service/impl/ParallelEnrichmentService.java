package com.bondarenko.movieland.service.impl;

import com.bondarenko.movieland.exceptions.ThreadInterruptedException;
import com.bondarenko.movieland.service.EnrichmentService;
import com.bondarenko.movieland.service.FutureService;
import com.bondarenko.movieland.service.dto.request.MovieDetailsDto;
import com.bondarenko.movieland.service.entity.common.TaskResult;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Slf4j
@Service
@Primary
@AllArgsConstructor
public class ParallelEnrichmentService implements EnrichmentService {
    private final long TASK_TIMEOUT = 5;
    private FutureService futureService;

    @Override
    public MovieDetailsDto enrichMovieDetailsDto(MovieDetailsDto movieDetailsDto, int movieId) {
        TaskResult taskResult = new TaskResult();
        List<Future<TaskResult>> futuresList = futureService.getFuturesList(movieId, taskResult);

        for (Future<TaskResult> future : futuresList) {
            try {
                TaskResult result = future.get(TASK_TIMEOUT, TimeUnit.SECONDS);
                enrichWithTaskResult(movieDetailsDto, result);
            } catch (TimeoutException e) {
                future.cancel(true);
            } catch (ExecutionException | InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new ThreadInterruptedException(Thread.currentThread());
            }
        }
        return movieDetailsDto;
    }

    void enrichWithTaskResult(MovieDetailsDto movieDetailsDto, TaskResult taskResult) {
        movieDetailsDto.setReviews(taskResult.getReviewDtos());
        movieDetailsDto.setGenres(taskResult.getGenreDtos());
        movieDetailsDto.setCountries(taskResult.getCountryDtos());
    }
}