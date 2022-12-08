package com.bondarenko.movieland.exceptions;


public class GenreNotFoundException extends RuntimeException {
    private static final String NO_GENRE_MESSAGE = "Genre is not found";

    public GenreNotFoundException() {
        super(String.format(NO_GENRE_MESSAGE));
    }
}