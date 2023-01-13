package com.bondarenko.movieland.repository;

import com.bondarenko.movieland.entity.Country;
import com.bondarenko.movieland.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {
    List<Country> findAll();

    Set<Country> findByIdIn(List<Integer> countryIds);

    @Query(value = "SELECT country.id, country.name FROM country  INNER JOIN movie_country ON country.id=movie_country.country_id  WHERE movie_country.movie_id=?;", nativeQuery = true)
    Set<Country> findByMovieId(int id);


}

