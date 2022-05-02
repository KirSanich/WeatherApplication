package com.example.weatherapplication.weather.service;

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
    public String getAdvice(String main) {
        return switch (main.toLowerCase(Locale.ROOT)) {
            case "rain" -> "\uD83C\uDF27Сейчас в этом городе дождливо, не забудьте взять зонт";
            case "snow" -> "❄️Сейчас в этом городе идет снег, не забудьте потеплее одеться";
            case "clear" -> "☀️Сейчас в этом городе ясное небо, можно гулять";
            case "drizzle" -> "❄️Сейчас в этом городе изморось, аккуратнее";
            case "thunderstorm" -> "\uD83C\uDF29️Сейчас в этом городе гроза, лучше переждать";
            case "clouds" -> "☁️Сейчас в этом городе облачно, возможно пасмурно";
            default -> "Не могу понять, какая сейчас в этом городе погода";
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
