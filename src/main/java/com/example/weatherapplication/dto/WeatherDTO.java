package com.example.weatherapplication.dto;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;


@Data

@RequiredArgsConstructor
public class WeatherDTO {

    private Coord coord;

    private static class Coord {
        private Double lan;
        private Double lon;
    }


   private List<Weather> weather;

    private static class Weather
    {
        private Long id;
        private String main;
        private String description;
        private String icon;
    }

    @Override
    public String toString() {
        return "WeatherDTO{" +
                "coord=" + coord +
                ", weather=" + weather +
                '}';
    }
}
