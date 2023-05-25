package com.bondarenko.movieland.mapper;

import com.bondarenko.movieland.dto.CountryDto;
import com.bondarenko.movieland.entity.Country;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountryMapperTest {
    private final CountryMapper genreMapper = Mappers.getMapper(CountryMapper.class);

    @Test
    @DisplayName("Mapping Country list to CountryDto list")
    void givenCountries_whenMapCountriesToCountryDtos_thenCountyDtosReturn() {
        Country countryFirst = Country.builder()
                .id(1)
                .name("Великобританія")
                .build();

        Country countrySecond = Country.builder()
                .id(2)
                .name("Україна")
                .build();

        Country countryThird = Country.builder()
                .id(3)
                .name("США")
                .build();
        List<CountryDto> countriesDtos = genreMapper.toCountryDtos(List.of(countryFirst, countrySecond, countryThird));

        assertEquals(3, countriesDtos.size());

        assertEquals(1, countriesDtos.get(0).getId());
        assertEquals(2, countriesDtos.get(1).getId());
        assertEquals(3, countriesDtos.get(2).getId());
        assertEquals("Великобританія", countriesDtos.get(0).getName());
        assertEquals("Україна", countriesDtos.get(1).getName());
        assertEquals("США", countriesDtos.get(2).getName());
    }
}