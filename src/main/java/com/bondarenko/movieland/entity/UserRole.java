package com.bondarenko.movieland.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserRole {
    ADMIN("ADMIN"),
    USER("USER");

    private final String name;
}