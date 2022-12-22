package com.bondarenko.movieland.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
public enum SortDirection {
    ASC("ASC"),
    DESC("DESC");

    @Getter
    private final String direction;
}