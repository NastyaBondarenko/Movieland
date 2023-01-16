package com.bondarenko.movieland.service.impl.callable;

import com.bondarenko.movieland.dto.TaskResultDto;
import com.bondarenko.movieland.service.GenreService;
import com.bondarenko.movieland.service.dto.request.MovieDetailsDto;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;

@Slf4j

public class GenreCallable implements Callable<MovieDetailsDto> {
    private static long WAITING_TASK_TIME = 5000;//change
    MovieDetailsDto movieDetailsDto;
    private final GenreService genreService;
    private final int movieId;

    public GenreCallable(GenreService genreService, int id, MovieDetailsDto movieDetailsDto) {
        this.genreService = genreService;
        this.movieId = id;
        this.movieDetailsDto = movieDetailsDto;
    }

    @Override
    public MovieDetailsDto call() throws Exception {
        log.info("[" + Thread.currentThread().getName() + "] FINDBY " + "call genres");
//        MovieDetailsDto taskResultDto = MovieDetailsDto.builder().build();
        movieDetailsDto.setGenres(genreService.findByMovieId(movieId));
        return movieDetailsDto;
    }
}