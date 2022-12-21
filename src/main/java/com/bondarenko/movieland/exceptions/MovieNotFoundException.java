package com.bondarenko.movieland.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MovieNotFoundException extends RuntimeException {

    private static final String NO_MOVIE_SORTED_BY_PARAMETER_MESSAGE = "There is no movie sorted by: %s";

    public MovieNotFoundException(String parameter) {
        super(String.format(NO_MOVIE_SORTED_BY_PARAMETER_MESSAGE, parameter));
    }
}