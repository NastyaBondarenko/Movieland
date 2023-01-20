package com.bondarenko.movieland.service.dto.request;

import com.bondarenko.movieland.dto.CountryDto;
import com.bondarenko.movieland.dto.GenreDto;
import com.bondarenko.movieland.dto.ReviewDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.micrometer.common.lang.NonNullFields;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@NonNullFields
public class MovieDetailsDto {
    @JsonProperty(index = 1)
    private int id;

    private String nameRussian;

    private String nameNative;

    @JsonFormat(pattern = "yyyy")
    private LocalDate yearOfRelease;

    private String description;

    private double rating;

    private double price;

    private String picturePath;

    private int votes;

    private Set<GenreDto> genres;

    private Set<CountryDto> countries;

    private Set<ReviewDto> reviews;
}
