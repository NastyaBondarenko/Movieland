package com.bondarenko.movieland.web.controller;

import com.bondarenko.movieland.util.CurrencyTypeConvertor;
import com.bondarenko.movieland.util.SortDirectionConvertor;
import com.bondarenko.movieland.dto.MovieDetailsDto;
import com.bondarenko.movieland.dto.MovieDto;
import com.bondarenko.movieland.entity.CurrencyType;
import com.bondarenko.movieland.entity.MovieRequest;
import com.bondarenko.movieland.entity.SortDirection;
import com.bondarenko.movieland.service.MovieService;
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
    protected List<MovieDto> getAll(@RequestParam(name = "price", required = false) SortDirection price,
                                    @RequestParam(name = "rating", required = false) SortDirection rating) {
        return movieService.findAll(new MovieRequest(price, rating, null));
    }

    @GetMapping("/random")
    protected List<MovieDto> getRandom() {
        return movieService.getRandom();
    }

    @GetMapping("/genre/{genreId}")
    protected List<MovieDto> getByGenre(@PathVariable("genreId") int genreId,
                                        @RequestParam(name = "price", required = false) SortDirection price,
                                        @RequestParam(name = "rating", required = false) SortDirection rating) {
        return movieService.getByGenre(new MovieRequest(price, rating, genreId));
    }

    @GetMapping("/{movieId}")
    protected MovieDetailsDto getByMovieId(@PathVariable("movieId") int movieId,
                                           @RequestParam(name = "currency", required = false) CurrencyType currency) {
        return movieService.getById(movieId, currency);
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(SortDirection.class, new SortDirectionConvertor());
        dataBinder.registerCustomEditor(CurrencyType.class, new CurrencyTypeConvertor());
    }
}
