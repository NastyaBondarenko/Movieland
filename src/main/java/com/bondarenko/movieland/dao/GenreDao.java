package com.bondarenko.movieland.dao;

import com.bondarenko.movieland.entity.Genre;

import java.util.List;

public interface GenreDao {

    List<Genre> findAll();
}
