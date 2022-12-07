package com.bondarenko.movieland.service.impl;

import com.bondarenko.movieland.dao.GenreDao;
import com.bondarenko.movieland.entity.Genre;
import com.bondarenko.movieland.entity.Movie;
import com.bondarenko.movieland.exceptions.GenreNotFoundException;
import com.bondarenko.movieland.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultGenreService implements GenreService {
    @Autowired
    private GenreDao genreDao;

    @Override
    public List<Genre> getAllGenres() {

        List<Genre> genres = genreDao.findAllGenres();
        return genres;
    }
}
