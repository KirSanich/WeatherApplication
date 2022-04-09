package com.example.weatherapplication.controller;


import com.example.weatherapplication.client.WeatherClient;
import com.example.weatherapplication.dto.WeatherDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    private final WeatherClient weatherClient;

    @Autowired
    public WeatherController(WeatherClient weatherClient) {
        this.weatherClient = weatherClient;
    }

    @GetMapping("/{lat}/{lon}")
    public ResponseEntity<WeatherDTO> getWeatherInTheDefinitelyRegion(@PathVariable Double lat, @PathVariable Double lon) {
        return new ResponseEntity<>(weatherClient.getWeatherByLatAndLon(lat, lon), HttpStatus.FOUND);
    }
}
