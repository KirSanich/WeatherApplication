package com.example.weatherapplication.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Service
@Slf4j
@AllArgsConstructor
public class WeatherServiceImpl implements WeatherService {

    @Override
    public Double fromKelvinToCelsius(Double kelvinTemp) {
        double scale = Math.pow(10, 2);
        return Math.ceil((kelvinTemp - 273.15) * scale) / scale;
    }

    @Override
    public String getAdvice(String description) {
        return switch (description.toLowerCase(Locale.ROOT)) {
            case "rain" -> "Сейчас дождливо, не забудьте взять зонт";
            case "snow" -> "Сейчас идет снег, не забудьте потеплее одеться";
            case "clear" -> "Сейчас ясное небо, можно гулять";
            case "drizzle" -> "Сейчас изморось, аккуратнее";
            case "thunderstorm" -> "Сейчас гроза, лучше переждать";
            case "clouds" -> "Сейчас облачно, возможно пасмурно";
            default -> "Непонятно какая сейчас погода";
        };
    }

    @Override
    public String fromUnixToZonedDate(Long UNIX) {
        Instant instant = Instant.ofEpochMilli(UNIX * 1000);
        ZoneId zoneId = ZoneId.of("Europe/Moscow");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        ZonedDateTime zonedDateTime = instant.atZone(zoneId);
        return zonedDateTime.format(dateTimeFormatter);
    }
}
