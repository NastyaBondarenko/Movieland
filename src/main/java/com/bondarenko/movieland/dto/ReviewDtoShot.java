package com.bondarenko.movieland.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ReviewDtoShot {
    private int movieId;
    private String description;
}