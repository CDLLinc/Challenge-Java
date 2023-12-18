package com.provincia.challenge.entities;

import com.provincia.challenge.forecast.entity.Forecast;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class ForecastEntityTest {

    @Test
    @Transactional
    public void testForecastConstructor() {
        // Creamos una instancia válida de la entidad Forecast usando el constructor
        Forecast forecast = new Forecast(1, "7414", LocalDateTime.now(), LocalDateTime.now().plusDays(1),
                "Previsión de chaparrones el lunes por la tarde", 5, 20, 40, "F", "Llovizna", "Tormentas");

        // Verificamos que los valores se hayan establecido correctamente
        assertThat(forecast.getId()).isEqualTo(1);
        assertThat(forecast.getLocationKey()).isEqualTo("7414");
        assertThat(forecast.getEffectiveDate()).isNotNull();
        assertThat(forecast.getEndDate()).isNotNull();
        assertThat(forecast.getText()).isEqualTo("Previsión de chaparrones el lunes por la tarde");
        assertThat(forecast.getSeverity()).isEqualTo(5);
        assertThat(forecast.getMinTemperature()).isEqualTo(20);
        assertThat(forecast.getMaxTemperature()).isEqualTo(40);
        assertThat(forecast.getUnitTemperature()).isEqualTo("F");
        assertThat(forecast.getDayIcon()).isEqualTo("Llovizna");
        assertThat(forecast.getNightIcon()).isEqualTo("Tormentas");
    }

    @Test
    @Transactional
    public void testForecastEmptyConstructor() {
        // Creamos una instancia de la entidad Forecast usando el constructor vacío
        Forecast forecast = new Forecast();

        // Verificamos que los valores iniciales sean los esperados
        assertThat(forecast.getId()).isEqualTo(0);
        assertThat(forecast.getLocationKey()).isNull();
        assertThat(forecast.getEffectiveDate()).isNull();
        assertThat(forecast.getEndDate()).isNull();
        assertThat(forecast.getText()).isNull();
        assertThat(forecast.getSeverity()).isEqualTo(0);
        assertThat(forecast.getMinTemperature()).isEqualTo(0);
        assertThat(forecast.getMaxTemperature()).isEqualTo(0);
        assertThat(forecast.getUnitTemperature()).isNull();
        assertThat(forecast.getDayIcon()).isNull();
        assertThat(forecast.getNightIcon()).isNull();
    }

    @Test
    @Transactional
    public void testForecastSettersAndGetters() {

        // Creamos una instancia válida de la entidad Forecast
        Forecast forecast = new Forecast();

        // Utilizamos los setters para establecer los valores
        forecast.setId(1);
        forecast.setLocationKey("7414");
        forecast.setEffectiveDate(LocalDateTime.now());
        forecast.setEndDate(LocalDateTime.now().plusDays(1));
        forecast.setText("Previsión de chaparrones el lunes por la tarde");
        forecast.setSeverity(5);
        forecast.setMinTemperature(20);
        forecast.setMaxTemperature(40);
        forecast.setUnitTemperature("Fahrenheit");
        forecast.setDayIcon("Llovizna");
        forecast.setNightIcon("Tormentas");

        // Verificamos que los getters devuelvan los valores establecidos
        assertThat(forecast.getId()).isEqualTo(1);
        assertThat(forecast.getLocationKey()).isEqualTo("7414");
        assertThat(forecast.getEffectiveDate()).isNotNull();
        assertThat(forecast.getEndDate()).isNotNull();
        assertThat(forecast.getText()).isEqualTo("Previsión de chaparrones el lunes por la tarde");
        assertThat(forecast.getSeverity()).isEqualTo(5);
        assertThat(forecast.getMinTemperature()).isEqualTo(20);
        assertThat(forecast.getMaxTemperature()).isEqualTo(40);
        assertThat(forecast.getUnitTemperature()).isEqualTo("Fahrenheit");
        assertThat(forecast.getDayIcon()).isEqualTo("Llovizna");
        assertThat(forecast.getNightIcon()).isEqualTo("Tormentas");
    }
}

