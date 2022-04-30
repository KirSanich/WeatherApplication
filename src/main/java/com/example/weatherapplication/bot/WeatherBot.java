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
public class WeatherBot extends TelegramLongPollingBot  {

    @Autowired
    private final WeatherController weatherController;

    @Autowired
    private final WeatherService weatherService;

    @Autowired
    private final BotConfiguration botConfiguration;

    @Autowired
    private final BotService botService;

    private BotStatus botStatus;

    public WeatherBot(WeatherController weatherController, WeatherService weatherService, BotConfiguration botConfiguration, BotService botService) {
        this.weatherController = weatherController;
        this.weatherService = weatherService;
        this.botStatus = BotStatus.FREE;
        this.botConfiguration = botConfiguration;
        this.botService = botService;
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

        Predicate<BotStatus> botStatusPredicate = botStatus1 -> botStatus1 == BotStatus.DO_COMMAND;
        Predicate<Message> messagePredicate = message1 -> message.hasText() && message.hasEntities();

        if (botStatusPredicate.test(botStatus)) {
            String answer = botService.createAnswer(message, BotStatus.DO_COMMAND);
            execute(SendMessage.builder().text(answer).chatId(message.getChatId().toString()).build());
            botStatus = BotStatus.FREE;
        } else if (messagePredicate.test(message)) {
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
                                    .text("Пожалуйста введи город для прогноза").chatId(message.getChatId().toString()).build());
                            botStatus = BotStatus.DO_COMMAND;
                        }

                    }
                    case "/set_welcome_israel" -> {
                        execute(SendMessage.builder().text("Шалом шабат для друзей из Тель-Авива")
                                .chatId(message.getChatId().toString()).build());
                    }
                }
            }
        }
    }

}
