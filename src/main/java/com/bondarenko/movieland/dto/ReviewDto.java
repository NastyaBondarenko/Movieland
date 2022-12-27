package com.bondarenko.movieland.dto;

import com.bondarenko.movieland.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewDto {

    private int id;
    private String description;
    private User user;
}