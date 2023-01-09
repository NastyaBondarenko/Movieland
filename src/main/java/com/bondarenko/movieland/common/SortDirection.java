package com.bondarenko.movieland.common;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
public enum SortDirection {
    ASC("ASC"),
    DESC("DESC");

    @Getter
    private final String direction;
}