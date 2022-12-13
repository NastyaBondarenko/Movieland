package com.bondarenko.movieland.service;

import com.bondarenko.movieland.dto.GenreDto;

import java.util.List;

public interface GenreService {
    List<GenreDto> findAll();
}
