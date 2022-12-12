package com.bondarenko.movieland.web.controller;

import com.bondarenko.movieland.dto.GenreDto;
import com.bondarenko.movieland.entity.Genre;
import com.bondarenko.movieland.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class GenreController {
    private final GenreService genreService;

    @GetMapping("/genre")
    protected List<Genre> findAll() {
        return genreService.findAll();
    }
}
