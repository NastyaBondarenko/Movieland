package com.bondarenko.movieland.repository;

import com.bondarenko.movieland.entity.Genre;

import java.util.List;


public interface GenreRepository {
    List<Genre> findAll();
}