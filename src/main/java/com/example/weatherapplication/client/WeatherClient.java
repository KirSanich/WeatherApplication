package com.example.weatherapplication.client;


import com.example.weatherapplication.dto.WeatherDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
public class WeatherClient {


    private RestTemplate restTemplate;

    private final String URL = "https://api.openweathermap.org/data/2.5/weather?lat=21&lon=24&appid=90480cbbb7f631012bd12004fcfbc094";

    public WeatherClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getWeatherByLatAndLon()
    {
       ResponseEntity<WeatherDTO> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, null, new ParameterizedTypeReference<WeatherDTO>() {});
       WeatherDTO weatherDTO = responseEntity.getBody();
       return Objects.requireNonNull(weatherDTO).toString();
    }

}
