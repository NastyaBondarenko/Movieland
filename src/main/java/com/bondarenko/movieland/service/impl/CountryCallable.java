package com.bondarenko.movieland.service.impl;

import com.bondarenko.movieland.dto.CountryDto;
import com.bondarenko.movieland.service.CountryService;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;
import java.util.concurrent.Callable;

@Slf4j
public class CountryCallable implements Callable<Set<CountryDto>> {
    private static final long WAITING_TASK_TIME = 5000;//change

    private final CountryService countryService;
    private final int movieId;

    public CountryCallable(CountryService countryService, int id) {
        this.countryService = countryService;
        this.movieId = id;
    }

    @Override
    public Set<CountryDto> call() throws Exception {
        log.info("[" + Thread.currentThread().getName() + "] FINDBY " + "call countries");
        return countryService.findByMovieId(movieId);
    }
}