package com.bondarenko.movieland.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
@AllArgsConstructor
public class MovieRequest {
    private SortDirection price;
    private SortDirection rating;
    private Integer genreId;
}