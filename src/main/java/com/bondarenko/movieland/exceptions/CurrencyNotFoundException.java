package com.bondarenko.movieland.exceptions;

import com.bondarenko.movieland.entity.CurrencyType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CurrencyNotFoundException extends RuntimeException {
    private static final String NO_CURRENCY_MESSAGE_BY_TYPE_MESSAGE = "There is no currency get by: %s";

    public CurrencyNotFoundException(CurrencyType currencyType) {
        super(String.format(NO_CURRENCY_MESSAGE_BY_TYPE_MESSAGE, currencyType));
    }
}