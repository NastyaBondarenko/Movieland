package com.bondarenko.movieland.service.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ReviewRequestDto {
    private int movieId;
    private String description;
}