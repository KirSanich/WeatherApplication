package com.example.weatherapplication.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@RequiredArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Clouds {

    @JsonProperty("all")
    private Long all;
}
