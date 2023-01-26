package com.bondarenko.movieland.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class GenreNotFoundException extends RuntimeException {
    private static final String NO_GENRE_BY_ID_MESSAGE = "There is no genre get by: %s";
    private static final String NO_GENRES_FOR_MOVIE_MESSAGE = "There is no genres for movie";

    public GenreNotFoundException(int genreId) {
        super(String.format(NO_GENRE_BY_ID_MESSAGE, genreId));
    }

    public GenreNotFoundException() {
        super(String.format(NO_GENRES_FOR_MOVIE_MESSAGE));
    }
}