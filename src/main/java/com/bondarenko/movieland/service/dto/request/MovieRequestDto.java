package com.bondarenko.movieland.service.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class MovieRequestDto {

    private String nameRussian;

    private String nameNative;

    private int yearOfRelease;

    private double price;

    private String description;

    private String picturePath;

    @JsonProperty("countryIds")
    private List<Integer> countryIds;

    @JsonProperty("genreIds")
    private List<Integer> genreIds;
}
