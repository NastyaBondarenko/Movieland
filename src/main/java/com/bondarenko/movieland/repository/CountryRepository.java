package com.bondarenko.movieland.repository;

import com.bondarenko.movieland.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {
    @Query("select c from Country c join fetch c.movie where c.id = :id")
    Country findByIdFetchMovie(@Param("id") int id);

}
