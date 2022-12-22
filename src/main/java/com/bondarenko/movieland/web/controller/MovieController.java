package com.bondarenko.movieland.web.controller;

import com.bondarenko.movieland.dto.MovieDto;
import com.bondarenko.movieland.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/movie", produces = MediaType.APPLICATION_JSON_VALUE)
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    protected List<MovieDto> getAll(@RequestParam Map<String, String> requestParameters) {
        return movieService.findAll(requestParameters);
    }

    @GetMapping("/random")
    protected List<MovieDto> getRandom() {
        return movieService.getRandom();
    }

    @GetMapping("/genre/{genreId}")
    protected List<MovieDto> getByGenre(@PathVariable("genreId") int genreId,
                                        @RequestParam Map<String, String> requestParameters) {
        return movieService.getByGenre(genreId, requestParameters);
    }
}
