package com.example.weatherapplication.configuration;


import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "application.properties")
@Getter
@Setter
public class WeatherConfiguration {

    @Value(value = "${application.apikey}")
    private String ApiKey;
}
