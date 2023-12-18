package com.provincia.challenge.dtos;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.provincia.challenge.forecast.dto.TemperatureDTO;
import com.provincia.challenge.forecast.dto.TemperatureValueDTO;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TemperatureDTOTest {

    @Test
    public void testAllArgsConstructor() {
        // Creamos instancias de la clase interna correspondiente
        TemperatureValueDTO min = new TemperatureValueDTO(20, "F");
        TemperatureValueDTO max = new TemperatureValueDTO(40, "F");

        // Creamos una instancia de TemperatureDTO usando el constructor con argumentos
        TemperatureDTO temperatureDTO = new TemperatureDTO(min, max);

        // Verificamos que los campos se hayan inicializado correctamente
        assertThat(temperatureDTO.getMinimum()).isEqualTo(min);
        assertThat(temperatureDTO.getMaximum()).isEqualTo(max);
    }

    @Test
    public void testNoArgsConstructor() {
        // Creamos una instancia de TemperatureDTO usando el constructor sin argumentos
        TemperatureDTO temperatureDTO = new TemperatureDTO();

        // Verificamos que los campos sean nulos
        assertThat(temperatureDTO.getMinimum()).isNull();
        assertThat(temperatureDTO.getMaximum()).isNull();
    }

    @Test
    public void testSettersAndGetters() {
        // Creamos instancias de la clase interna correspondiente
        TemperatureValueDTO min = new TemperatureValueDTO(20, "F");
        TemperatureValueDTO max = new TemperatureValueDTO(40, "F");

        // Creamos una instancia de TemperatureDTO
        TemperatureDTO temperatureDTO = new TemperatureDTO();

        // Utilizamos setters para establecer los valores
        temperatureDTO.setMinimum(min);
        temperatureDTO.setMaximum(max);

        // Verificamos que los getters devuelvan los valores establecidos
        assertThat(temperatureDTO.getMinimum()).isEqualTo(min);
        assertThat(temperatureDTO.getMaximum()).isEqualTo(max);
    }

    @Test
    public void testJsonDeserialization() throws Exception {
        // Creamos un JSON que represente un objeto TemperatureDTO
        String json = "{\"Minimum\":{\"value\":20,\"unit\":\"F\"},\"Maximum\":{\"value\":40,\"unit\":\"F\"}}";

        // Utilizamos ObjectMapper para deserializar el JSON a un objeto TemperatureDTO
        ObjectMapper objectMapper = new ObjectMapper();
        TemperatureDTO temperatureDTO = objectMapper.readValue(json, TemperatureDTO.class);

        // Verificamos que los campos se hayan deserializado correctamente
        assertThat(temperatureDTO.getMinimum().getValue()).isEqualTo(20);
        assertThat(temperatureDTO.getMinimum().getUnit()).isEqualTo("F");
        assertThat(temperatureDTO.getMaximum().getValue()).isEqualTo(40);
        assertThat(temperatureDTO.getMaximum().getUnit()).isEqualTo("F");
    }
}

