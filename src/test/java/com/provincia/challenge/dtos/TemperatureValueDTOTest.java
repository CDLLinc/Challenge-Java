package com.provincia.challenge.dtos;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.provincia.challenge.forecast.dto.TemperatureValueDTO;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TemperatureValueDTOTest {

    @Test
    public void testAllArgsConstructor() {
        // Creamos una instancia de TemperatureValueDTO usando el constructor con argumentos
        TemperatureValueDTO temperatureValueDTO = new TemperatureValueDTO(25, "F");

        // Verificamos que los campos se hayan inicializado correctamente
        assertThat(temperatureValueDTO.getValue()).isEqualTo(25);
        assertThat(temperatureValueDTO.getUnit()).isEqualTo("F");
    }

    @Test
    public void testNoArgsConstructor() {
        // Creamos una instancia de TemperatureValueDTO usando el constructor sin argumentos
        TemperatureValueDTO temperatureValueDTO = new TemperatureValueDTO();

        // Verificamos que los campos sean nulos o tengan los valores predeterminados según sea el caso
        assertThat(temperatureValueDTO.getValue()).isEqualTo(0);
        assertThat(temperatureValueDTO.getUnit()).isNull();
    }

    @Test
    public void testSettersAndGetters() {
        // Creamos una instancia de TemperatureValueDTO
        TemperatureValueDTO temperatureValueDTO = new TemperatureValueDTO();

        // Utilizamos setters para establecer los valores
        temperatureValueDTO.setValue(30);
        temperatureValueDTO.setUnit("F");

        // Verificamos que los getters devuelvan los valores establecidos
        assertThat(temperatureValueDTO.getValue()).isEqualTo(30);
        assertThat(temperatureValueDTO.getUnit()).isEqualTo("F");
    }

    @Test
    public void testJsonDeserialization() throws Exception {
        // Creamos un JSON que represente un objeto TemperatureValueDTO
        String json = "{\"Value\":25,\"Unit\":\"F\"}";

        // Utilizamos ObjectMapper para deserializar el JSON a un objeto TemperatureValueDTO
        ObjectMapper objectMapper = new ObjectMapper();
        TemperatureValueDTO temperatureValueDTO = objectMapper.readValue(json, TemperatureValueDTO.class);

        // Verificamos que los campos se hayan deserializado correctamente
        assertThat(temperatureValueDTO.getValue()).isEqualTo(25);
        assertThat(temperatureValueDTO.getUnit()).isEqualTo("F");
    }
}
