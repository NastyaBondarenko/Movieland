package com.bondarenko.movieland.service.impl;

import com.bondarenko.movieland.dto.CountryDto;
import com.bondarenko.movieland.entity.Country;
import com.bondarenko.movieland.mapper.CountryMapper;
import com.bondarenko.movieland.repository.CountryRepository;
import com.bondarenko.movieland.service.CountryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class DefaultCountryService implements CountryService {

    private CountryRepository countryRepository;
    private CountryMapper countryMapper;

    @Override
    @Transactional(readOnly = true)
    public List<CountryDto> findAll() {
        List<Country> countries = countryRepository.findAll();
        return countryMapper.toCountryDtos(countries);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Country> findByIdIn(List<Integer> countryIds) {
        return countryRepository.findByIdIn(countryIds);
    }
}