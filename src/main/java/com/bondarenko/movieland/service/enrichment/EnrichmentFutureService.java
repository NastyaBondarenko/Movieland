package com.bondarenko.movieland.service.enrichment;

import com.bondarenko.movieland.dto.CountryDto;
import com.bondarenko.movieland.dto.GenreDto;
import com.bondarenko.movieland.dto.ReviewDto;
import com.bondarenko.movieland.service.CountryService;
import com.bondarenko.movieland.service.FutureService;
import com.bondarenko.movieland.service.GenreService;
import com.bondarenko.movieland.service.ReviewService;
import com.bondarenko.movieland.service.entity.common.EnrichmentTaskResult;
import com.google.common.annotations.VisibleForTesting;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@AllArgsConstructor
@Service
public class EnrichmentFutureService implements FutureService {
    private final CountryService countryService;
    private final GenreService genreService;
    private final ReviewService reviewService;
    private final ExecutorService executorService = Executors.newCachedThreadPool();

    @Override
    public List<Future<EnrichmentTaskResult>> getFuturesList(int id, EnrichmentTaskResult enrichmentTaskResult) {
        Future<EnrichmentTaskResult> futureCountryDtos = getFutureCountryDtos(id, enrichmentTaskResult);
        Future<EnrichmentTaskResult> futureReviewDtos = getFutureReviewDtos(id, enrichmentTaskResult);
        Future<EnrichmentTaskResult> futureGenreDtos = getFutureGenreDtos(id, enrichmentTaskResult);
        return List.of(futureCountryDtos, futureReviewDtos, futureGenreDtos);
    }

    @VisibleForTesting
    Future<EnrichmentTaskResult> getFutureCountryDtos(int movieId, EnrichmentTaskResult enrichmentTaskResult) {
        Set<CountryDto> countryDto = countryService.findByMovieId(movieId);
        enrichmentTaskResult.setCountryDtos(countryDto);
        return CompletableFuture.supplyAsync(() -> enrichmentTaskResult, executorService);
    }

    @VisibleForTesting
    Future<EnrichmentTaskResult> getFutureGenreDtos(int movieId, EnrichmentTaskResult enrichmentTaskResult) {
        Set<GenreDto> genreDtos = genreService.findByMovieId(movieId);
        enrichmentTaskResult.setGenreDtos(genreDtos);
        return CompletableFuture.supplyAsync(() -> enrichmentTaskResult, executorService);
    }

    @VisibleForTesting
    Future<EnrichmentTaskResult> getFutureReviewDtos(int movieId, EnrichmentTaskResult enrichmentTaskResult) {
        Set<ReviewDto> reviewDtos = reviewService.findByMovieId(movieId);
        enrichmentTaskResult.setReviewDtos(reviewDtos);
        return CompletableFuture.supplyAsync(() -> enrichmentTaskResult, executorService);
    }
}