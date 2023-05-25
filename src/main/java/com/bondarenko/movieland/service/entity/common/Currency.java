package com.bondarenko.movieland.service.entity.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Currency {
    @JsonProperty("r030")
    private int serialNumber;

    @JsonProperty("txt")
    private String currencyName;

    @JsonProperty("cc")
    private String currencyType;

    @JsonProperty("exchangedate")
    private String exchangeDate;

    private double rate;

}