package com.bondarenko.movieland.service.enrichment;

import com.bondarenko.movieland.dto.GenreDto;
import com.bondarenko.movieland.service.GenreService;
import com.bondarenko.movieland.service.entity.common.EnrichmentResult;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;
import java.util.concurrent.Callable;

@Slf4j
@AllArgsConstructor
public class EnrichByGenreCallable implements Callable<EnrichmentResult> {
    private EnrichmentResult enrichmentResult;
    private GenreService genreService;
    private int movieId;

    @Override
    public EnrichmentResult call() {
        Set<GenreDto> genreDtos = genreService.findByMovieId(movieId);
        enrichmentResult.setGenreDtos(genreDtos);
        log.info("Enrich by genreDtos in {}", Thread.currentThread().getName());
        return enrichmentResult;
    }
}