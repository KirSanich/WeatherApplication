package com.example.weatherapplication.bot;

import org.telegram.telegrambots.meta.api.objects.Message;

public interface BotService {

    String createAnswer(Message message, BotStatus botStatus);
}
