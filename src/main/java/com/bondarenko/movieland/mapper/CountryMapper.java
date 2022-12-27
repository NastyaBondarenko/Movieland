package com.bondarenko.movieland.mapper;

import com.bondarenko.movieland.dto.CountryDto;
import com.bondarenko.movieland.entity.Country;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper
public interface CountryMapper {

    Set<CountryDto> toCountryDtos(Set<Country> genres);
}