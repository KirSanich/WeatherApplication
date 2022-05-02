package com.example.weatherapplication.weather.service;

public interface WeatherService {

    Double fromKelvinToCelsius(Double kelvinTemp);

    String getAdvice(String main);

    String fromUnixToZonedDate(Long UNIX);
}
