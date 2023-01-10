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

    @JsonProperty(index = 2)
    private String nameRussian;

    @JsonProperty(index = 3)
    private String nameNative;

    @JsonProperty(index = 4)
    @JsonFormat(pattern = "yyyy")
    private LocalDate yearOfRelease;

    @JsonProperty(index = 5)
    private String description;

    @JsonProperty(index = 6)
    private double rating;

    @JsonProperty(index = 7)
    private double price;

    @JsonProperty(index = 8)
    private String picturePath;

    @JsonProperty(index = 9)
    private int votes;

    @JsonProperty(index = 10)
    private Set<GenreDto> genres;

    @JsonProperty(index = 11)
    private Set<CountryDto> countries;

    @JsonProperty(index = 12)
    private Set<ReviewDto> reviews;
}
