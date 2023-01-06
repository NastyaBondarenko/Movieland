package com.bondarenko.movieland.service;

import com.bondarenko.movieland.dto.GenreDto;
import com.bondarenko.movieland.entity.Genre;

import java.util.List;
import java.util.Set;

public interface GenreService {
    List<GenreDto> findAll();

    Genre findGenreById(int genreId);

    Set<Genre> findByIdIn(List<Integer> genreIds);
}