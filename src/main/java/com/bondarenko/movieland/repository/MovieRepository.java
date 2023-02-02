package com.bondarenko.movieland.repository;

import com.bondarenko.movieland.entity.Genre;
import com.bondarenko.movieland.entity.Movie;
import com.bondarenko.movieland.service.dto.request.MovieRequestDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer>, CustomMovieRepository {
    Movie findEnrichedMovieById(int movieId);

    Movie findEnrichedMovieByCountriesAndGenres(int movieId, MovieRequestDto movieRequestDto);

    List<Movie> findMoviesByGenresIn(Set<Genre> genre);
}