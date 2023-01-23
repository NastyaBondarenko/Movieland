package com.bondarenko.movieland.service.enrichment;

import com.bondarenko.movieland.dto.CountryDto;
import com.bondarenko.movieland.dto.GenreDto;
import com.bondarenko.movieland.dto.ReviewDto;
import com.bondarenko.movieland.service.CountryService;
import com.bondarenko.movieland.service.EnrichmentService;
import com.bondarenko.movieland.service.GenreService;
import com.bondarenko.movieland.service.ReviewService;
import com.bondarenko.movieland.service.dto.request.MovieDetailsDto;
import com.bondarenko.movieland.service.entity.common.EnrichmentResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Slf4j
@Service
@Primary
@RequiredArgsConstructor
public class ParallelEnrichmentService implements EnrichmentService {
    private final ExecutorService executorService = Executors.newCachedThreadPool();
    private final CountryService countryService;
    private final GenreService genreService;
    private final ReviewService reviewService;
    @Value("${task.timeout.seconds:5}")
    private int taskTimeout;

    @Override
    public MovieDetailsDto enrichMovieDetailsDto(MovieDetailsDto movieDetailsDto) {
        int movieId = movieDetailsDto.getId();
        EnrichmentResult enrichmentResult = new EnrichmentResult();

        EnrichByGenreCallable enrichByGenreCallable = new EnrichByGenreCallable(enrichmentResult, genreService, movieId);
        EnrichByCountryCallable enrichByCountryCallable = new EnrichByCountryCallable(enrichmentResult, countryService, movieId);
        EnrichByReviewCallable enrichByReviewCallable = new EnrichByReviewCallable(enrichmentResult, reviewService, movieId);

        List<Callable<EnrichmentResult>> tasks = List.of(enrichByGenreCallable, enrichByCountryCallable, enrichByReviewCallable);

        List<Future<EnrichmentResult>> taskResults;
        try {
            taskResults = executorService.invokeAll(tasks, taskTimeout, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        List<EnrichmentResult> enrichmentResults = getEnrichmentResults(taskResults);
        enrichByTaskResults(enrichmentResults, movieDetailsDto);
        executorService.shutdown();
        return movieDetailsDto;
    }

    private List<EnrichmentResult> getEnrichmentResults(List<Future<EnrichmentResult>> taskResults) {
        return taskResults.stream().map(future -> {
            try {
                return future.get(taskTimeout, TimeUnit.SECONDS);
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            } catch (TimeoutException e) {
                future.cancel(true);
                log.info("Task is cancelled {}", future.isCancelled());
                throw new RuntimeException(e);
            }
        }).toList();

    }

    private void enrichByTaskResults(List<EnrichmentResult> enrichmentResults, MovieDetailsDto movieDetailsDto) {
        Set<ReviewDto> reviewDtos = enrichmentResults.stream()
                .map(EnrichmentResult::getReviewDtos)
                .filter(Objects::nonNull).findFirst().get();

        Set<GenreDto> genreDtos = enrichmentResults.stream()
                .map(EnrichmentResult::getGenreDtos)
                .filter(Objects::nonNull).findFirst().get();

        Set<CountryDto> countryDtos = enrichmentResults.stream()
                .map(EnrichmentResult::getCountryDtos)
                .filter(Objects::nonNull).findFirst().get();

        movieDetailsDto.setCountries(countryDtos);
        movieDetailsDto.setGenres(genreDtos);
        movieDetailsDto.setReviews(reviewDtos);
    }
}