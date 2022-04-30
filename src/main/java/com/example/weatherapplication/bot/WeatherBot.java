package com.example.weatherapplication.bot;

import com.example.weatherapplication.controller.WeatherController;
import com.example.weatherapplication.dto.WeatherDTO;
import com.example.weatherapplication.service.WeatherService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;
import java.util.function.Predicate;

@Component
@Getter
@Setter
public class WeatherBot extends TelegramLongPollingBot implements BotCommand {

    @Autowired
    private final WeatherController weatherController;

    @Autowired
    private final WeatherService weatherService;

    @Autowired
    private final BotConfiguration botConfiguration;

    private BotStatus botStatus;

    public WeatherBot(WeatherController weatherController, WeatherService weatherService) {
        this.weatherController = weatherController;
        this.weatherService = weatherService;
        this.botStatus = BotStatus.FREE;
        this.botConfiguration = new BotConfiguration();
    }

    @Override
    public String getBotUsername() {
        return botConfiguration.getUsername();
    }

    @Override
    public String getBotToken() {
        return botConfiguration.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            handleMessage(update.getMessage());
        }
    }

    @SneakyThrows
    private void handleMessage(Message message) {

        Predicate<Message> botFreeAndExpressionHas = message1 -> (message.hasText() && (message.getText().startsWith("Бот, узнай погоду в городе ")) && botStatus.equals(BotStatus.FREE));

        if (botFreeAndExpressionHas.test(message)) {
            execute(SendMessage.builder()
                    .text(createAnswer(message, BotStatus.FREE))
                    .chatId(message.getChatId().toString())
                    .build());
        } else if (botStatus == BotStatus.DO_COMMAND) {
            String answer = createAnswer(message, BotStatus.DO_COMMAND);
            execute(SendMessage.builder().text(answer).chatId(message.getChatId().toString()).build());
            botStatus = BotStatus.FREE;
        } else if (message.hasText() && message.hasEntities()) {
            Optional<MessageEntity> commandEntity = message.getEntities().stream()
                    .filter(e -> "bot_command".equalsIgnoreCase(e.getType()))
                    .findFirst();
            if (commandEntity.isPresent()) {
                String command = message.getText()
                        .substring(commandEntity.get().getOffset(), commandEntity.get().getLength());
                switch (command) {
                    case "/set_weather" -> {
                        {
                            execute(SendMessage.builder()
                                    .text("Пожалуйста введите город:").chatId(message.getChatId().toString()).build());
                            botStatus = BotStatus.DO_COMMAND;
                        }

                    }
                    case "/set_welcome_israel" -> {
                        Message message1 = execute(SendMessage.builder().text("Шалом шабат для друзей из Тель-Авива")
                                .chatId(message.getChatId().toString()).build());
                    }
                }
            }
        }
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
                        + "Температура в Цельсиях: " + weatherService.fromKelvinToCelsius(weatherDTO.getMain().getTemp()) + "\n"
                        + "\n"
                        + weatherService.getAdvice(weatherDTO.getWeather().get(0).getMain()) + "\n"
                        + "\n"
                        + "-----------------";
            }
            case DO_COMMAND -> {
                String city = message.getText();
                WeatherDTO weatherDTO = weatherController.getWeather(city).getBody();
                yield  "-----------------" + "\n"
                        + "\n"
                        + "Город: " + weatherDTO.getName() + "\n"
                        + "Температура в Цельсиях: " + weatherService.fromKelvinToCelsius(weatherDTO.getMain().getTemp()) + "\n"
                        + "\n"
                        + weatherService.getAdvice(weatherDTO.getWeather().get(0).getMain()) + "\n"
                        + "\n"
                        + "-----------------";
            }
        };
    }
}
