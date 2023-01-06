package com.bondarenko.movieland.web.controller;

import com.bondarenko.movieland.dto.CountryDto;
import com.bondarenko.movieland.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/country", produces = MediaType.APPLICATION_JSON_VALUE)
public class CountryController {

    private final CountryService countryService;

    @GetMapping
    protected List<CountryDto> getAll() {
        return countryService.findAll();
    }
}