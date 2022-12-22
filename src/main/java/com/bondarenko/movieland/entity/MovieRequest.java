package com.bondarenko.movieland.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MovieRequest {
    private String price;
    private String rating;
    private Integer genreId;
}