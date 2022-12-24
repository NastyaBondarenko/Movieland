package com.bondarenko.movieland.dto;

import com.bondarenko.movieland.entity.Country;
import com.bondarenko.movieland.entity.Genre;
import com.bondarenko.movieland.entity.Review;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MovieDetailsDto {
    private int id;

    private String nameRussian;

    private String nameNative;

    private int yearOfRelease;

    private String description;

    private double rating;

    private double price;

    private String picturePath;

    private int votes;

    private Set<Genre> genre = new HashSet<>();

    private List<Country> countries = new ArrayList<>();

    private List<Review> reviews = new ArrayList<>();
}
