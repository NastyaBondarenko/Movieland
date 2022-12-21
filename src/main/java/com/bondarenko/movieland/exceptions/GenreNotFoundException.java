package com.bondarenko.movieland.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class GenreNotFoundException extends RuntimeException {
    private static final String NO_GENRE_MESSAGE = "Genre is not found";

    public GenreNotFoundException() {
        super(String.format(NO_GENRE_MESSAGE));
    }
}