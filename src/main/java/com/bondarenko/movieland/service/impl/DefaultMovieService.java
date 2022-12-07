package com.bondarenko.movieland.service.impl;

import com.bondarenko.movieland.dao.MovieDao;
import com.bondarenko.movieland.entity.Genre;
import com.bondarenko.movieland.entity.Movie;
import com.bondarenko.movieland.exceptions.GenreNotFoundException;
import com.bondarenko.movieland.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class DefaultMovieService implements MovieService {

    private final int randomMoviesLength = 3;

    @Autowired
    private MovieDao movieDao;

    @Override
    public List<Movie> findAll() {
        return movieDao.findAll();
    }

    @Override
    public List<Movie> getRandomMovies() {
        List<Movie> movies = movieDao.findAll();
        Collections.shuffle(movies);
        return movies.subList(0, randomMoviesLength);
    }

    @Override
    public List<Movie> getByGenre(int genreId) {
        return movieDao.findByGenreId(genreId);
    }
}
