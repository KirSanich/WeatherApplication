package com.example.weatherapplication.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class Main {

    @JsonProperty("temp")
    private Double temp;

    @JsonProperty("feels_like")
    private Double feels_like;

    @JsonProperty("temp_min")
    private Double temp_min;

    @JsonProperty("temp_max")
    private Double temp_max;

    @JsonProperty("pressure")
    private Long pressure;


    @JsonProperty("humidity")
    private Long humidity;
}
