package com.bondarenko.movieland.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MovieDto {

    private int id;

    private String nameRussian;

    private String nameNative;

    private LocalDate yearOfRelease;

    private String description;

    private double rating;

    private double price;

    private String picturePath;

    private int votes;
}
