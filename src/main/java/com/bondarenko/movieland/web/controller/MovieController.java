package com.bondarenko.movieland.web.controller;

import com.bondarenko.movieland.entity.Movie;
import com.bondarenko.movieland.service.MovieService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/movie", produces = MediaType.APPLICATION_JSON_VALUE)
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    protected List<Movie> getAll() {
        return movieService.findAll();
    }

    @GetMapping("/random")
    protected List<Movie> getRandomMovie() {
        return movieService.getRandomMovies();
    }

    @GetMapping("/genre/{genreId}")
    protected List<Movie> getByGenre(@PathVariable("genreId") int genreId) {
        return movieService.getByGenre(genreId);
    }
}
