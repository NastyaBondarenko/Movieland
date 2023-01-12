package com.bondarenko.movieland.service.impl;

import com.bondarenko.movieland.AbstractBaseITest;
import com.bondarenko.movieland.dto.CountryDto;
import com.bondarenko.movieland.entity.Country;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DBRider
@AutoConfigureMockMvc(addFilters = false)
public class DefaultCountryServiceITest extends AbstractBaseITest {

    @Autowired
    private DefaultCountryService countryService;

    @Test
    @DataSet(value = "datasets/country/dataset_countries.yml", cleanAfter = true,
            cleanBefore = true, skipCleaningFor = "flyway_schema_history")
    @DisplayName("when Find All then Country Dtos Returned")
    public void whenFindAll_thenCountryDtosReturned() {
        List<CountryDto> actualCountryDtoList = countryService.findAll();

        CountryDto actualCountryDtoFirst = actualCountryDtoList.get(0);
        CountryDto actualCountryDtoSecond = actualCountryDtoList.get(1);
        CountryDto actualCountryDtoThird = actualCountryDtoList.get(2);
        CountryDto actualCountryDtoFourth = actualCountryDtoList.get(3);

        assertEquals(4, actualCountryDtoList.size());
        assertEquals(1, actualCountryDtoFirst.getId());
        assertEquals("США", actualCountryDtoFirst.getName());
        assertEquals(2, actualCountryDtoSecond.getId());
        assertEquals("Франция", actualCountryDtoSecond.getName());
        assertEquals(3, actualCountryDtoThird.getId());
        assertEquals("Великобритания", actualCountryDtoThird.getName());
        assertEquals(4, actualCountryDtoFourth.getId());
        assertEquals("Италия", actualCountryDtoFourth.getName());
    }

    @Test
    @DataSet(value = "datasets/country/dataset_countries.yml", cleanAfter = true,
            cleanBefore = true, skipCleaningFor = "flyway_schema_history")
    @DisplayName("when Find All then Country Dtos Returned")
    public void whenFindAll_thenCoudntryDtosReturned() {
        List<Integer> countryIds = List.of(1);
        Set<Country> actualCountries = countryService.findByIdIn(countryIds);

        Country actualCountry = actualCountries.stream().findFirst().get();
        assertEquals(1, actualCountries.size());
        assertEquals(1, actualCountry.getId());
        assertEquals("США", actualCountry.getName());
    }
}