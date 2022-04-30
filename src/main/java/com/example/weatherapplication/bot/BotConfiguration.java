package com.example.weatherapplication.bot;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "application.bot")
@Getter
@Setter
public class BotConfiguration {

    @Value("${bot.username}")
    private String username;

    @Value("${bot.token}")
    private String token;
}
