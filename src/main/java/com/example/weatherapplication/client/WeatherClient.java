package com.example.weatherapplication.client;


import com.example.weatherapplication.dto.WeatherDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class WeatherClient {


    private final RestTemplate restTemplate;

    private final String URL = "https://api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appid=90480cbbb7f631012bd12004fcfbc094";

    @Autowired
    public WeatherClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getWeatherByLatAndLon(Double lat, Double lon) {
        Map<String,String> map = new HashMap<>();
        map.put("lat",lat.toString());
        map.put("lon",lon.toString());
        WeatherDTO responseEntity = restTemplate.getForObject(URL,WeatherDTO.class,map);
        return Objects.requireNonNull(responseEntity).toString();
    }

}
