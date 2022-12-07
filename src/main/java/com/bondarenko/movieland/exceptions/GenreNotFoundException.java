package com.bondarenko.movieland.exceptions;


public class GenreNotFoundException extends RuntimeException {

    private static final String NO_GENRE_BY_ID_MESSAGE = "There is no genre with id: %s";
    private static final String NO_GENRE_MESSAGE = "Genre is not found";

    public GenreNotFoundException() {
        super(String.format(NO_GENRE_MESSAGE));
    }

    public GenreNotFoundException(int genreId) {
        super(String.format(NO_GENRE_BY_ID_MESSAGE, genreId));
    }
}