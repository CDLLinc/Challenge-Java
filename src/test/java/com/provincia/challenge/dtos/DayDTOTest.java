package com.provincia.challenge.dtos;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.provincia.challenge.forecast.dto.DayDTO;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DayDTOTest {

    @Test
    public void testAllArgsConstructor() {
        // Creamos una instancia de DayDTO usando el constructor con argumentos
        DayDTO dayDTO = new DayDTO("Llovizna");

        // Verificamos que el campo se haya inicializado correctamente
        assertThat(dayDTO.getIconPhrase()).isEqualTo("Llovizna");
    }

    @Test
    public void testNoArgsConstructor() {
        // Creamos una instancia de DayDTO usando el constructor sin argumentos
        DayDTO dayDTO = new DayDTO();

        // Verificamos que el campo sea nulo
        assertThat(dayDTO.getIconPhrase()).isNull();
    }

    @Test
    public void testSettersAndGetters() {
        // Creamos una instancia de DayDTO
        DayDTO dayDTO = new DayDTO();

        // Utilizamos el setter para establecer el valor
        dayDTO.setIconPhrase("Tormentas");

        // Verificamos que el getter devuelva el valor establecido
        assertThat(dayDTO.getIconPhrase()).isEqualTo("Tormentas");
    }

    @Test
    public void testJsonDeserialization() throws Exception {
        // Creamos un JSON que represente un objeto DayDTO
        String json = "{\"IconPhrase\":\"Lluvia\"}";

        // Utilizamos ObjectMapper para deserializar el JSON a un objeto DayDTO
        ObjectMapper objectMapper = new ObjectMapper();
        DayDTO dayDTO = objectMapper.readValue(json, DayDTO.class);

        // Verificamos que el campo se haya deserializado correctamente
        assertThat(dayDTO.getIconPhrase()).isEqualTo("Lluvia");
    }
}
