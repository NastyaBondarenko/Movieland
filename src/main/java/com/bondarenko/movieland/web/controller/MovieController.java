package com.bondarenko.movieland.web.controller;

import com.bondarenko.movieland.dto.MovieDetailsDto;
import com.bondarenko.movieland.dto.MovieDto;
import com.bondarenko.movieland.dto.MovieDtoShort;
import com.bondarenko.movieland.entity.CurrencyType;
import com.bondarenko.movieland.entity.MovieRequest;
import com.bondarenko.movieland.common.SortDirection;
import com.bondarenko.movieland.service.MovieService;
import com.bondarenko.movieland.util.CurrencyTypeConvertor;
import com.bondarenko.movieland.util.SortDirectionConvertor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/movie", produces = MediaType.APPLICATION_JSON_VALUE)
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    protected List<MovieDto> findAll(MovieRequest movieRequest) {
        return movieService.findAll(movieRequest);
    }

    @GetMapping("/random")
    protected List<MovieDto> findRandom() {
        return movieService.findRandom();
    }

    @GetMapping("/genre/{genreId}")
    protected List<MovieDto> findByGenre(MovieRequest movieRequest) {
        return movieService.findByGenre(movieRequest);
    }

    @GetMapping("/{movieId}")
    protected MovieDetailsDto findByMovieId(@PathVariable("movieId") int movieId,
                                            @RequestParam(name = "currency", required = false) CurrencyType currency) {
        return movieService.findById(movieId, currency);
    }

    @PostMapping
    protected void add(@RequestBody MovieDtoShort movieDtoShort) {
        movieService.add(movieDtoShort);
    }

    @PutMapping("/{movieId}")
    protected void update(@PathVariable("movieId") int movieId, @RequestBody MovieDtoShort movieDtoShort) {
        movieService.update(movieDtoShort, movieId);
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(SortDirection.class, new SortDirectionConvertor());
        dataBinder.registerCustomEditor(CurrencyType.class, new CurrencyTypeConvertor());
    }
}