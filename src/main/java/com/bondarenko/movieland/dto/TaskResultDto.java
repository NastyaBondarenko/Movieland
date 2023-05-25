package com.bondarenko.movieland.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class TaskResultDto {

    Set<ReviewDto> reviewDtoSet;
    Set<GenreDto> genreDtos;
    Set<CountryDto> countryDtos;
}
