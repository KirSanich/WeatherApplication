package com.example.weatherapplication.client;


import com.example.weatherapplication.dto.WeatherDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "weather-api", url = "${application.weather.url}")
public interface WeatherClient {

    @GetMapping(value = "/weather?q={city_name}&appid={Api_key}")
    WeatherDTO getWeatherByCity(@PathVariable("city_name") String cityName, @PathVariable("Api_key") String ApiKey);
}
