package com.bondarenko.movieland.service.impl.callable;

import com.bondarenko.movieland.service.CountryService;
import com.bondarenko.movieland.service.dto.request.MovieDetailsDto;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;

@Slf4j
public class CountryCallable implements Callable<MovieDetailsDto> {
    private static final long WAITING_TASK_TIME = 5000;//change
    MovieDetailsDto movieDetailsDto;
    private final CountryService countryService;
    private final int movieId;

    public CountryCallable(CountryService countryService, int id, MovieDetailsDto movieDetailsDto) {
        this.countryService = countryService;
        this.movieId = id;
        this.movieDetailsDto = movieDetailsDto;
    }

    @Override
    public MovieDetailsDto call() throws Exception {
        log.info("[" + Thread.currentThread().getName() + "] countries in thread ");
        movieDetailsDto.setCountries(countryService.findByMovieId(movieId));
        return movieDetailsDto;

    }
}