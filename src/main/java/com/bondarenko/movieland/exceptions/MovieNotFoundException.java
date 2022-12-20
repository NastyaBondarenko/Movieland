package com.bondarenko.movieland.exceptions;


public class MovieNotFoundException extends RuntimeException {

    private static final String NO_MOVIE_SORTED_BY_PARAMETER_MESSAGE = "There is no movie sorted by: %s";

    public MovieNotFoundException(String parameter) {
        super(String.format(NO_MOVIE_SORTED_BY_PARAMETER_MESSAGE, parameter));
    }

    public MovieNotFoundException(String parameter) {
        super(String.format(NO_MOVIE_SORTED_BY_PARAMETER_MESSAGE, parameter));
    }
}