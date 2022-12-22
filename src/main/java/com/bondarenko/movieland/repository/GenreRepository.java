package com.bondarenko.movieland.repository;

import com.bondarenko.movieland.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface GenreRepository extends JpaRepository<Genre, Integer> {
    List<Genre> findAll();

    Optional<Genre> findGenreById(int id);
}