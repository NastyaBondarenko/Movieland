package com.bondarenko.movieland.service.entity.common;

import com.bondarenko.movieland.entity.Country;
import com.bondarenko.movieland.entity.Genre;
import com.bondarenko.movieland.entity.Review;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class EnrichmentResult {
    private Set<Country> countries;
    private Set<Review> reviews;
    private Set<Genre> genres;
}