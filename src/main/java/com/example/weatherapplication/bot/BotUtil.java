package com.example.weatherapplication.bot;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BotUtil {

    private final static List<String> greetings = List.of("Привет",
            "Добрый день",
            "Добрый вечер",
            "Ку",
            "Хей",
            "Здарова");

    public List<String> getGreetings()
    {
        return greetings;
    }

}
