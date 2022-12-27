package com.bondarenko.movieland.repository;

import com.bondarenko.movieland.entity.Genre;
import com.bondarenko.movieland.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

    List<Movie> findMoviesByGenresIn(Set<Genre> genre);

    Optional<Movie> findMovieById(int id);
}