package com.bondarenko.movieland.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class GenreNotFoundException extends RuntimeException {
    private static final String NO_GENRE_MESSAGE_BY_ID_MESSAGE = "There is no genre get by: %s";

    public GenreNotFoundException(int genreId) {
        super(String.format(NO_GENRE_MESSAGE_BY_ID_MESSAGE, genreId));
    }
}