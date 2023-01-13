package com.bondarenko.movieland.service.impl;

import com.bondarenko.movieland.dto.GenreDto;
import com.bondarenko.movieland.service.GenreService;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;
import java.util.concurrent.Callable;

@Slf4j

public class GenreCallable implements Callable<Set<GenreDto>> {
    private static long WAITING_TASK_TIME = 5000;//change

    private final GenreService genreService;
    private final int movieId;

    public GenreCallable(GenreService genreService, int id) {
        this.genreService = genreService;
        this.movieId = id;
    }


    @Override
    public Set<GenreDto> call() throws Exception {
        log.info("[" + Thread.currentThread().getName() + "] FINDBY " + "call genres");
        return genreService.findByMovieId(movieId);
    }
}