package com.provincia.challenge.dtos;

import com.provincia.challenge.forecast.dto.*;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DailyForecastDTOTest {

    @Test
    public void testAllArgsConstructor() {

        // Creamos instancias de las clases internas correspondientes
        TemperatureValueDTO min = new TemperatureValueDTO(20, "F");
        TemperatureValueDTO max = new TemperatureValueDTO(40, "F");
        TemperatureDTO temperatureDTO = new TemperatureDTO(min, max);
        DayDTO dayDTO = new DayDTO("Llovizna");
        NightDTO nightDTO = new NightDTO("Tormentas");

        // Creamos una instancia de DailyForecastDTO
        DailyForecastDTO dailyForecastDTO = new DailyForecastDTO(temperatureDTO, dayDTO, nightDTO);

        // Verificamos que los campos se hayan inicializado correctamente
        assertThat(dailyForecastDTO.getTemperature()).isEqualTo(temperatureDTO);
        assertThat(dailyForecastDTO.getDay()).isEqualTo(dayDTO);
        assertThat(dailyForecastDTO.getNight()).isEqualTo(nightDTO);
    }

    @Test
    public void testNoArgsConstructor() {
        // Creamos una instancia de DailyForecastDTO usando el constructor sin argumentos
        DailyForecastDTO dailyForecastDTO = new DailyForecastDTO();

        // Verificamos que los campos sean nulos
        assertThat(dailyForecastDTO.getTemperature()).isNull();
        assertThat(dailyForecastDTO.getDay()).isNull();
        assertThat(dailyForecastDTO.getNight()).isNull();
    }

    @Test
    public void testSettersAndGetters() {
        // Creamos instancias de las clases internas correspondientes
        TemperatureValueDTO min = new TemperatureValueDTO(20, "F");
        TemperatureValueDTO max = new TemperatureValueDTO(40, "F");
        TemperatureDTO temperatureDTO = new TemperatureDTO(min, max);
        DayDTO dayDTO = new DayDTO("Llovizna");
        NightDTO nightDTO = new NightDTO("Tormentas");

        // Crear una instancia de DailyForecastDTO
        DailyForecastDTO dailyForecastDTO = new DailyForecastDTO();

        // Utilizamos setters para establecer los valores
        dailyForecastDTO.setTemperature(temperatureDTO);
        dailyForecastDTO.setDay(dayDTO);
        dailyForecastDTO.setNight(nightDTO);

        // Verificamos que los getters devuelvan los valores establecidos
        assertThat(dailyForecastDTO.getTemperature()).isEqualTo(temperatureDTO);
        assertThat(dailyForecastDTO.getDay()).isEqualTo(dayDTO);
        assertThat(dailyForecastDTO.getNight()).isEqualTo(nightDTO);
    }
}
