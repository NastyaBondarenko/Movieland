package com.bondarenko.movieland.repository;

import com.bondarenko.movieland.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {
    List<Country> findAll();

    Set<Country> findByIdIn(List<Integer> countryIds);
}