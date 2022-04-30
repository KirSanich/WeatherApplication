package com.example.weatherapplication.bot;


import com.example.weatherapplication.client.WeatherClient;
import com.example.weatherapplication.controller.WeatherController;
import com.example.weatherapplication.dto.WeatherDTO;
import com.example.weatherapplication.service.WeatherService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.function.Predicate;

@Service
@Slf4j
@AllArgsConstructor
public class BotServiceImpl implements BotService{

    @Autowired
    private final WeatherController weatherController;

    @Autowired
    private final WeatherClient weatherClient;

    @Autowired
    private final WeatherService weatherService;

    @Override
    public boolean chooseTypeAnswer(Predicate<Message> messagePredicate) {
        return false;
    }

    @Override
    public String createAnswer(Message message, BotStatus botStatus) {
        return switch (botStatus) {
            case FREE -> {
                String city = message.getText().substring(27);
                WeatherDTO weatherDTO = weatherController.getWeather(city).getBody();
                yield "-----------------" + "\n"
                        + "\n"
                        + "Город: " + weatherDTO.getName() + "\n"
                        + "Температура: " + weatherService.fromKelvinToCelsius(weatherDTO.getMain().getTemp()) + "C" + "\n"
                        + "Ветер: " + weatherDTO.getWind().getSpeed() + "м/c" + "\n"
                        + "Восход: " + weatherService.fromUnixToZonedDate(weatherDTO.getSys().getSunrise()) + "\n"
                        + "Закат: " + weatherService.fromUnixToZonedDate(weatherDTO.getSys().getSunset()) + "\n"
                        + "\n"
                        + weatherService.getAdvice(weatherDTO.getWeather().get(0).getMain()) + "\n"
                        + "\n"
                        + "-----------------";
            }
            case DO_COMMAND -> {
                String city = message.getText();
                WeatherDTO weatherDTO = weatherController.getWeather(city).getBody();
                yield "-----------------" + "\n"
                        + "\n"
                        + "Город: " + weatherDTO.getName() + "\n"
                        + "Температура: " + weatherService.fromKelvinToCelsius(weatherDTO.getMain().getTemp()) + "C" + "\n"
                        + "Ветер: " + weatherDTO.getWind().getSpeed() + "м/c" + "\n"
                        + "Восход: " + weatherService.fromUnixToZonedDate(weatherDTO.getSys().getSunrise()) + "\n"
                        + "Закат: " + weatherService.fromUnixToZonedDate(weatherDTO.getSys().getSunset()) + "\n"
                        + "\n"
                        + weatherService.getAdvice(weatherDTO.getWeather().get(0).getMain()) + "\n"
                        + "\n"
                        + "-----------------";
            }
        };
    }
}
