package com.example.weatherapplication.weather.exception;


import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class WeatherGlobalAdvice {

    @ExceptionHandler(FeignException.NotFound.class)
    public ResponseEntity<?> FeignNotFoundException() {
        return new ResponseEntity<>("No such city found, please try again and check input", HttpStatus.NOT_FOUND);
    }

}
