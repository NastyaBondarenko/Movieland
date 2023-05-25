package com.bondarenko.movieland.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
    private static final String NO_USER_FOUND_BY_TYPE_EMAIL = "There is no user get by: %s";

    public UserNotFoundException(String email) {
        super(String.format(NO_USER_FOUND_BY_TYPE_EMAIL, email));
    }
}