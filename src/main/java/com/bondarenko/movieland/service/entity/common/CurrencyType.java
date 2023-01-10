package com.bondarenko.movieland.service.entity.common;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
public enum CurrencyType {
    UAH("UAH"),
    USD("USD"),
    EUR("EUR");

    @Getter
    private final String type;
}