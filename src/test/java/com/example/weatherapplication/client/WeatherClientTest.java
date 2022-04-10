package com.example.weatherapplication.client;

import com.example.weatherapplication.WeatherApplication;
import com.example.weatherapplication.dto.WeatherDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;


@RestClientTest(WeatherClient.class)
@RunWith(SpringRunner.class)
class WeatherClientTest {

    @Autowired
    private WeatherClient weatherClient;

    @Autowired
    private MockRestServiceServer server;

    @Autowired
    private ObjectMapper objectMapper;

    @SneakyThrows
    @Test
    @DisplayName("Should return not null dto")
    void whenCallingGetWeatherDetails_thenClientMakesCorrectCall() {
        String json = """
                {
                    "coord": {
                        "lat": 51.0,
                        "lon": 39.0
                    },
                    "weather": [
                        {
                            "id": 804,
                            "main": "Clouds",
                            "description": "overcast clouds",
                            "icon": "04d"
                        }
                    ],
                    "base": "stations",
                    "main": {
                        "temp": 288.46,
                        "feels_like": 287.46,
                        "temp_min": 288.46,
                        "temp_max": 288.46,
                        "pressure": 1008,
                        "humidity": 54
                    },
                    "visibility": 10000,
                    "wind": {
                        "speed": 7.47,
                        "deg": 204
                    },
                    "clouds": {
                        "all": 100
                    },
                    "dt": 1649581858,
                    "sys": {
                        "type": null,
                        "id": null,
                        "message": null,
                        "country": "RU",
                        "sunrise": 1649558420,
                        "sunset": 1649606999
                    },
                    "timezone": 10800,
                    "id": 514198,
                    "name": "Ostrogozhsk",
                    "cod": 200
                }
                """;

        WeatherDTO actual = objectMapper.readValue(json,WeatherDTO.class);
        this.server.expect(requestTo( "https://api.openweathermap.org/data/2.5/weather?lat=51.0&lon=39.0&appid=90480cbbb7f631012bd12004fcfbc094")).andRespond(withSuccess(json, MediaType.APPLICATION_JSON));
        WeatherDTO weatherDTO = weatherClient.getWeatherByLatAndLon(51.0,39.0);
        assertEquals(weatherDTO,actual);
        assertThat(actual).usingRecursiveComparison().isEqualTo(weatherDTO);

    }
}