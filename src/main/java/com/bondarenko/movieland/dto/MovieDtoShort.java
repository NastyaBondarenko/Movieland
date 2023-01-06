package com.bondarenko.movieland.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MovieDtoShort {

    private String nameRussian;

    private String nameNative;

    private int yearOfRelease;

    private double price;

    private String description;

    private String picturePath;

    private List<Integer> countries;

    private List<Integer> genres;
}
