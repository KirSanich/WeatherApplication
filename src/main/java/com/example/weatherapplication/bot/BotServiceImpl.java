package com.example.weatherapplication.bot;


import com.example.weatherapplication.weather.client.WeatherClient;
import com.example.weatherapplication.weather.controller.WeatherController;
import com.example.weatherapplication.weather.dto.WeatherDTO;
import com.example.weatherapplication.weather.service.WeatherService;
import feign.FeignException;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.time.Year;
import java.util.InputMismatchException;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class BotServiceImpl implements BotService {

    @Autowired
    private final WeatherController weatherController;

    @Autowired
    private final BotUtil botUtil;

    @Autowired
    private final WeatherService weatherService;

    @SneakyThrows
    @Override
    public String createAnswer(Message message, BotStatus botStatus) {
        return switch (botStatus) {
            case FREE -> {
                if(!message.hasEntities()) {
                    String inputFromUser = message.getText();
                    Optional<String> greeting = botUtil.getGreetings().stream()
                            .filter(greeting1 -> greeting1.equalsIgnoreCase(inputFromUser))
                            .findAny();
                    if (greeting.isPresent())
                        yield "Привет";
                    else yield "Не могу тебя понять";
                }
                else throw new InputMismatchException();
            }
            case DO_COMMAND -> {
                String city = message.getText();
                try {
                    WeatherDTO weatherDTO = weatherController.getWeather(city).getBody();
                    yield "-----------------" + "\n"
                            + "\n"
                            + "✅Город: " + weatherDTO.getName() + "\n"
                            + "✅Температура: " + weatherService.fromKelvinToCelsius(weatherDTO.getMain().getTemp()) + "C" + "\n"
                            + "✅Ветер: " + weatherDTO.getWind().getSpeed() + "м/c" + "\n"
                            + "✅Восход: " + weatherService.fromUnixToZonedDate(weatherDTO.getSys().getSunrise()) + "\n"
                            + "✅Закат: " + weatherService.fromUnixToZonedDate(weatherDTO.getSys().getSunset()) + "\n"
                            + "\n"
                            + weatherService.getAdvice(weatherDTO.getWeather().get(0).getMain()) + "\n"
                            + "\n"
                            + "-----------------";
                }
                catch (FeignException.NotFound e)
                {
                    yield "Такого города не существует, повторите попытку позже или проверьте корректность введеных данных";
                }
            }
        };
    }

}
