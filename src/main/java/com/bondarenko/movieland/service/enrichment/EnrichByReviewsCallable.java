package com.bondarenko.movieland.service.enrichment;

import com.bondarenko.movieland.entity.Review;
import com.bondarenko.movieland.service.ReviewService;
import com.bondarenko.movieland.service.entity.common.EnrichmentResult;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;
import java.util.concurrent.Callable;

@Slf4j
@AllArgsConstructor
public class EnrichByReviewsCallable implements Callable<EnrichmentResult> {
    private EnrichmentResult enrichmentResult;
    private ReviewService reviewService;
    private int movieId;

    @Override
    public EnrichmentResult call() {
        Set<Review> reviews = reviewService.findByMovieId(movieId);
        enrichmentResult.setReviews(reviews);
        log.info("Enrich by reviews in {}", Thread.currentThread().getName());
        return enrichmentResult;
    }
}