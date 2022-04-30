package com.example.weatherapplication.service;

public interface WeatherService {

    Double fromKelvinToCelsius(Double kelvinTemp);

    String getAdvice(String description);

    String fromUnixToZonedDate(Long UNIX);
}
