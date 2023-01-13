package com.bondarenko.movieland.mapper;

import com.bondarenko.movieland.dto.CountryDto;
import com.bondarenko.movieland.entity.Country;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

@Mapper
public interface CountryMapper {
    List<CountryDto> toCountryDtos(List<Country> countries);

    Set<CountryDto> toCountryDtos(Set<Country> countries);
}