package com.example.weatherapplication.weather.client;


import com.example.weatherapplication.weather.dto.WeatherDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(value = "weather-api", url = "${application.weather.url}")
public interface WeatherClient {

    @GetMapping(value = "/weather?q={city_name}&appid={Api_key}")
    WeatherDTO getWeatherByCity(@PathVariable("city_name") String cityName, @PathVariable("Api_key") String ApiKey);

}
