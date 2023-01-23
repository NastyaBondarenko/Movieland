package com.bondarenko.movieland.service.enrichment;

import com.bondarenko.movieland.dto.CountryDto;
import com.bondarenko.movieland.service.CountryService;
import com.bondarenko.movieland.service.entity.common.EnrichmentResult;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;
import java.util.concurrent.Callable;

@Slf4j
@AllArgsConstructor
public class EnrichByCountryCallable implements Callable<EnrichmentResult> {
    private EnrichmentResult enrichmentResult;
    private CountryService countryService;
    private int movieId;

    @Override
    public EnrichmentResult call() {
        Set<CountryDto> countryDtos = countryService.findByMovieId(movieId);
        enrichmentResult.setCountryDtos(countryDtos);
        log.info("Enrich by countryDtos in {}", Thread.currentThread().getName());
        return enrichmentResult;
    }
}