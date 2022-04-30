package com.example.weatherapplication.bot;

import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.function.Predicate;

public interface BotService {

    boolean chooseTypeAnswer(Predicate<Message> messagePredicate);

    String createAnswer(Message message, BotStatus botStatus);
}
