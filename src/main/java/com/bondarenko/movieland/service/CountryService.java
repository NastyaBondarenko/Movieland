package com.bondarenko.movieland.service;

import com.bondarenko.movieland.dto.CountryDto;
import com.bondarenko.movieland.entity.Country;

import java.util.List;
import java.util.Set;

public interface CountryService {
    List<CountryDto> findAll();

    Set<Country> findByIdIn(List<Integer> countryIds);
}