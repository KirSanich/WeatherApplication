package com.example.weatherapplication.weather.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@RequiredArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Coord {
    @JsonProperty("lat")
    private Double lat;
    @JsonProperty("lon")
    private Double lon;

}
