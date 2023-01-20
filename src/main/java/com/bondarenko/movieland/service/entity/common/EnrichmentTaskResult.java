package com.bondarenko.movieland.service.entity.common;

import com.bondarenko.movieland.dto.CountryDto;
import com.bondarenko.movieland.dto.GenreDto;
import com.bondarenko.movieland.dto.ReviewDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class EnrichmentTaskResult {
    private Set<CountryDto> countryDtos;
    private Set<ReviewDto> reviewDtos;
    private Set<GenreDto> genreDtos;
}