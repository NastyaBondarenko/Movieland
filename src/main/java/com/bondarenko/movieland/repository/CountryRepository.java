package com.bondarenko.movieland.repository;

import com.bondarenko.movieland.entity.Country;
import com.bondarenko.movieland.entity.Genre;
import com.bondarenko.movieland.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {
//    List<Movie> findMoviesByGenreIn(Set<Genre> genre);
//    List<Country> findCountriesByMovie(Movie movie);

//    @Query("select c from Country c where c.movie.id =:id")
//    List<Country> findCountriesByMovieIsIn(Movie movie);

//   List<Country> findByMovieId(int id);
}
