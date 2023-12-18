package com.provincia.challenge.dtos;

import com.provincia.challenge.forecast.dto.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ForecastDTOTest {

    @Test
    public void testAllArgsConstructor() {

        // Creamos instancias de las clases internas correspondientes
        HeadlineDTO headlineDTO = new HeadlineDTO("2023-12-18T13:00:00-03:00", "2023-12-18T19:00:00-03:00", "Previsión de chaparrones el lunes por la tarde", 5);
        TemperatureValueDTO min = new TemperatureValueDTO(20, "F");
        TemperatureValueDTO max = new TemperatureValueDTO(40, "F");
        TemperatureDTO temperatureDTO = new TemperatureDTO(min, max);
        DayDTO dayDTO = new DayDTO("Llovizna");
        NightDTO nightDTO = new NightDTO("Tormentas");
        DailyForecastDTO dailyForecastDTO = new DailyForecastDTO(temperatureDTO, dayDTO, nightDTO);
        List<DailyForecastDTO> listDailyForecast = new ArrayList<>();
        listDailyForecast.add(dailyForecastDTO);


        // Creamos una instancia de ForecastDTO usando el constructor con argumentos
        ForecastDTO forecastDTO = new ForecastDTO(headlineDTO, listDailyForecast);

        // Verificamos que los campos se hayan inicializado correctamente
        assertThat(forecastDTO.getHeadline()).isEqualTo(headlineDTO);
        assertThat(forecastDTO.getDailyForecasts()).isEqualTo(listDailyForecast);
    }

    @Test
    public void testNoArgsConstructor() {
        // Creamos una instancia de ForecastDTO usando el constructor sin argumentos
        ForecastDTO forecastDTO = new ForecastDTO();

        // Verificamos que los campos sean nulos
        assertThat(forecastDTO.getHeadline()).isNull();
        assertThat(forecastDTO.getDailyForecasts()).isNull();
    }

    @Test
    public void testSettersAndGetters() {
        // Crear instancias de las clases internas correspondientes
        HeadlineDTO headlineDTO = new HeadlineDTO("2023-12-18T13:00:00-03:00", "2023-12-18T19:00:00-03:00", "Previsión de chaparrones el lunes por la tarde", 5);
        TemperatureValueDTO min = new TemperatureValueDTO(20, "F");
        TemperatureValueDTO max = new TemperatureValueDTO(40, "F");
        TemperatureDTO temperatureDTO = new TemperatureDTO(min, max);
        DayDTO dayDTO = new DayDTO("Llovizna");
        NightDTO nightDTO = new NightDTO("Tormentas");
        DailyForecastDTO dailyForecastDTO = new DailyForecastDTO(temperatureDTO, dayDTO, nightDTO);
        List<DailyForecastDTO> listDailyForecast = new ArrayList<>();
        listDailyForecast.add(dailyForecastDTO);

        // Creamos una instancia de ForecastDTO
        ForecastDTO forecastDTO = new ForecastDTO();

        // Utilizamos setters para establecer los valores
        forecastDTO.setHeadline(headlineDTO);
        forecastDTO.setDailyForecasts(listDailyForecast);

        // Verificamos que los getters devuelvan los valores establecidos
        assertThat(forecastDTO.getHeadline()).isEqualTo(headlineDTO);
        assertThat(forecastDTO.getDailyForecasts()).isEqualTo(listDailyForecast);
    }

}
