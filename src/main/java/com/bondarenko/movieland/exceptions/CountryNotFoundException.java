package com.bondarenko.movieland.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CountryNotFoundException extends RuntimeException {
    private static final String NO_COUNTRY_FOR_MOVIE_MESSAGE = "There is no country for movie";

    public CountryNotFoundException() {
        super(String.format(NO_COUNTRY_FOR_MOVIE_MESSAGE));
    }
}