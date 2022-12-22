package com.bondarenko.movieland.web.controller;

import com.bondarenko.movieland.dto.MovieDto;
import com.bondarenko.movieland.entity.MovieRequest;
import com.bondarenko.movieland.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/movie", produces = MediaType.APPLICATION_JSON_VALUE)
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    protected List<MovieDto> getAll(@RequestParam(name = "price", required = false) String price,
                                    @RequestParam(name = "rating", required = false) String rating) {
        return movieService.findAll(new MovieRequest(price, rating, null));
    }

    @GetMapping("/random")
    protected List<MovieDto> getRandom() {
        return movieService.getRandom();
    }

//    @GetMapping("/genre/{genreId}")
//    protected List<MovieDto> getByGenre(@PathVariable("genreId") int genreId,
//                                        @RequestParam Map<String, String> requestParameters) {
//        return movieService.getByGenre(first,second);
//    }
}
