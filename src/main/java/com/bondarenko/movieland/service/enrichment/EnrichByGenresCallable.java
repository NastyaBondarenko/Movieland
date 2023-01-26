package com.bondarenko.movieland.service.enrichment;

import com.bondarenko.movieland.entity.Genre;
import com.bondarenko.movieland.service.GenreService;
import com.bondarenko.movieland.service.entity.common.EnrichmentResult;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;
import java.util.concurrent.Callable;

@Slf4j
@AllArgsConstructor
public class EnrichByGenresCallable implements Callable<EnrichmentResult> {
    private EnrichmentResult enrichmentResult;
    private GenreService genreService;
    private int movieId;

    @Override
    public EnrichmentResult call() {
        Set<Genre> genres = genreService.findByMovieId(movieId);
        enrichmentResult.setGenres(genres);
        log.info("Enrich by genres in {}", Thread.currentThread().getName());
        return enrichmentResult;
    }
}