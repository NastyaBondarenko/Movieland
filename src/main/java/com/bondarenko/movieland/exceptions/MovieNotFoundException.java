package com.bondarenko.movieland.exceptions;


public class MovieNotFoundException extends RuntimeException {

    private static final String NO_MOVIE_SORTED_BY_PARAMETER_MESSAGE = "There is no movie sorted by parameter: %s";
    private static final String NO_MOVIE_MESSAGE = "Movie is not found";

    public MovieNotFoundException() {
        super(String.format(NO_MOVIE_MESSAGE));
    }

    public MovieNotFoundException(String parameter) {
        super(String.format(NO_MOVIE_SORTED_BY_PARAMETER_MESSAGE, parameter));
    }
}