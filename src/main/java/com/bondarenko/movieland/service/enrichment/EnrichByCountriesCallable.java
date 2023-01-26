package com.bondarenko.movieland.service.enrichment;

import com.bondarenko.movieland.entity.Country;
import com.bondarenko.movieland.service.CountryService;
import com.bondarenko.movieland.service.entity.common.EnrichmentResult;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;
import java.util.concurrent.Callable;

@Slf4j
@AllArgsConstructor
public class EnrichByCountriesCallable implements Callable<EnrichmentResult> {
    private EnrichmentResult enrichmentResult;
    private CountryService countryService;
    private int movieId;

    @Override
    public EnrichmentResult call() {
        Set<Country> countries = countryService.findByMovieId(movieId);
        enrichmentResult.setCountries(countries);
        log.info("Enrich by countries in {}", Thread.currentThread().getName());
        return enrichmentResult;
    }
}