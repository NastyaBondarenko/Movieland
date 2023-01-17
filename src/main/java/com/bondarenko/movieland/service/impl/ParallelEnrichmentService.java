package com.bondarenko.movieland.service.impl;

import com.bondarenko.movieland.service.CompletableFutureService;
import com.bondarenko.movieland.service.EnrichmentService;
import com.bondarenko.movieland.service.dto.request.MovieDetailsDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
@Service
@Primary
@AllArgsConstructor
public class ParallelEnrichmentService implements EnrichmentService {
    private final long TASK_TIMEOUT = 5;
    private CompletableFutureService completableFutureService;

    @Override
    public MovieDetailsDto enrichMovieDetailsDto(MovieDetailsDto movieDetailsDto, int movieId) {

        List<CompletableFuture<MovieDetailsDto>> futuresList = completableFutureService
                .getCompletableFuturesList(movieId, movieDetailsDto);

        CompletableFuture<Void> completableFuturesResult = CompletableFuture.allOf(futuresList
                .toArray(new CompletableFuture[0]));

        try {
            completableFuturesResult.get(TASK_TIMEOUT, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            AtomicBoolean cancelled = new AtomicBoolean(false);
            cancelled.set(true);
            Thread.currentThread().interrupt();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        Optional<MovieDetailsDto> movieDetailsDtoOptional = futuresList
                .stream()
                .filter(future -> future.isDone() && !future.isCompletedExceptionally() && !future.isCancelled())
                .map(CompletableFuture::join)
                .findFirst();

        return movieDetailsDtoOptional.get();
    }
}




//      try {
//              completableFuturesResult.get(0, TimeUnit.SECONDS);
//              } catch (ExecutionException | InterruptedException | TimeoutException exception) {
//              throw new RuntimeException(exception);
//              }