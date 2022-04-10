package com.example.weatherapplication.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;


@Data
@Getter
@Setter
@RequiredArgsConstructor
@EqualsAndHashCode
public class WeatherDTO {

    @JsonProperty("coord")
    private Coord coord;

    @JsonProperty("weather")
    private List<Weather> weather;

    @JsonProperty("base")
    private String base;

    @JsonProperty("main")
    private Main main;

    @JsonProperty("visibility")
    private Long visibility;

    @JsonProperty("wind")
    private Wind wind;

    @JsonProperty("clouds")
    private Clouds clouds;

    @JsonProperty("dt")
    private Long dt;

    @JsonProperty("sys")
    private Sys sys;

    @JsonProperty("timezone")
    private Long timezone;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("cod")
    private Long cod;

    @Override
    public String toString() {
        return "coord:" + this.getCoord().toString() + "\n" +
                "weather:" + this.getWeather().toString() + "\n" +
                "base:" + this.getBase() + "\n" +
                "main:" + this.getMain().toString() + "\n" +
                "visibility:" + this.getVisibility().toString() + "\n" +
                "wind:" + this.getWind().toString() + "\n" +
                "clouds:" + this.getClouds().toString() + "\n" +
                "dt:" + this.getDt().toString() + "\n" +
                "sys:" + this.getSys().toString() + "\n" +
                "timezone:" + this.getTimezone().toString() + "\n" +
                "id:" + this.getId().toString() + "\n" +
                "name:" + this.getName() + "\n" +
                "cod:" + this.getCod().toString() + "\n";


    }
}
