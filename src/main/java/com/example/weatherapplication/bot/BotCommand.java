package com.example.weatherapplication.bot;

import com.example.weatherapplication.dto.WeatherDTO;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface BotCommand {

    String createAnswer(Message message, BotStatus botStatus);
}
