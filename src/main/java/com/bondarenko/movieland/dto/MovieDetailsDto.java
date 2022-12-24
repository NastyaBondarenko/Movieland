package com.bondarenko.movieland.dto;

import com.bondarenko.movieland.entity.Country;
import com.bondarenko.movieland.entity.Genre;
import com.bondarenko.movieland.entity.Movie;
import com.bondarenko.movieland.entity.Review;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class MovieDetailsDto {

    private Movie movie;

    private List<Country> countries = new ArrayList<>();

    private Set<Genre> genres = new HashSet<>();

    private List<Review> reviews = new ArrayList<>();
}
