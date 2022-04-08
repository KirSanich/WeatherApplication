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
public class Coord {
    @JsonProperty("lat")
    private Double lat;
    @JsonProperty("lon")
    private Double lon;

}
