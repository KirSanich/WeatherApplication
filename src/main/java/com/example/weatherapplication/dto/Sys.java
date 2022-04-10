package com.example.weatherapplication.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@RequiredArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Sys {

    @JsonProperty("type")
    private Long type;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("message")
    private Double message;

    @JsonProperty("country")
    private String country;

    @JsonProperty("sunrise")
    private Long sunrise;

    @JsonProperty("sunset")
    private Long sunset;

}
