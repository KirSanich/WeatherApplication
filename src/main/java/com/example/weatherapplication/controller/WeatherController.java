package com.example.weatherapplication.controller;


import com.example.weatherapplication.client.WeatherClient;
import com.example.weatherapplication.configuration.WeatherConfiguration;
import com.example.weatherapplication.dto.WeatherDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class WeatherController {

    @Autowired
    private final WeatherClient weatherClient;

    @Autowired
    private final WeatherConfiguration weatherConfiguration;

    @GetMapping("/weather/{city_name}")
    public ResponseEntity<WeatherDTO> getWeather(@PathVariable("city_name") String cityName)
    {
        return ResponseEntity.ok(weatherClient.getWeatherByCity(cityName,weatherConfiguration.getApiKey()));
    }
}
