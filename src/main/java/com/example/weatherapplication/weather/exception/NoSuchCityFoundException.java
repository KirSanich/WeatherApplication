package com.example.weatherapplication.weather.exception;

public class NoSuchCityFoundException extends RuntimeException{
    public NoSuchCityFoundException() {
    }

    public NoSuchCityFoundException(String message) {
        super(message);
    }

    public NoSuchCityFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchCityFoundException(Throwable cause) {
        super(cause);
    }
}
