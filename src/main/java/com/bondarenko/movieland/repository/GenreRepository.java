package com.bondarenko.movieland.repository;

import com.bondarenko.movieland.entity.Genre;

import java.util.List;
import java.util.Optional;


public interface GenreRepository {
    List<Genre> findAll();

    Optional<Genre> findGenreById(int id);
}