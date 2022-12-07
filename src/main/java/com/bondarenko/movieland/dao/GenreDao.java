package com.bondarenko.movieland.dao;

import com.bondarenko.movieland.entity.Genre;
import com.bondarenko.movieland.entity.Movie;

import java.util.List;
import java.util.Optional;

public interface GenreDao {

    List<Genre> findAllGenres();

}
