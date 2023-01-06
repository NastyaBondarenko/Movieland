package com.bondarenko.movieland.service;

import com.bondarenko.movieland.dto.GenreDto;
import com.bondarenko.movieland.entity.Genre;

import java.util.List;

public interface GenreService {
    List<GenreDto> findAll();

    Genre findGenreById(int genreId);
}
