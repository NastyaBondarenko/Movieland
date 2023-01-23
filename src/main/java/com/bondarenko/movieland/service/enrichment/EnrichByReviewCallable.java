package com.bondarenko.movieland.service.enrichment;

import com.bondarenko.movieland.dto.ReviewDto;
import com.bondarenko.movieland.service.ReviewService;
import com.bondarenko.movieland.service.entity.common.EnrichmentResult;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;
import java.util.concurrent.Callable;

@Slf4j
@AllArgsConstructor
public class EnrichByReviewCallable implements Callable<EnrichmentResult> {
    private EnrichmentResult enrichmentResult;
    private ReviewService reviewService;
    private int movieId;

    @Override
    public EnrichmentResult call() {
        Set<ReviewDto> reviewDtos = reviewService.findByMovieId(movieId);
        enrichmentResult.setReviewDtos(reviewDtos);
        log.info("Enrich by reviewDtos in {}", Thread.currentThread().getName());
        return enrichmentResult;
    }
}