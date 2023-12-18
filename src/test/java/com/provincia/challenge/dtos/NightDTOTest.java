package com.provincia.challenge.dtos;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.provincia.challenge.forecast.dto.NightDTO;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NightDTOTest {

    @Test
    public void testAllArgsConstructor() {
        // Creamos una instancia de NightDTO usando el constructor con argumentos
        NightDTO nightDTO = new NightDTO("Tormentas");

        // Verificamos que el campo se haya inicializado correctamente
        assertThat(nightDTO.getIconPhrase()).isEqualTo("Tormentas");
    }

    @Test
    public void testNoArgsConstructor() {
        // Creamos una instancia de NightDTO usando el constructor sin argumentos
        NightDTO nightDTO = new NightDTO();

        // Verificamos que el campo sea nulo
        assertThat(nightDTO.getIconPhrase()).isNull();
    }

    @Test
    public void testSettersAndGetters() {
        // Creamos una instancia de NightDTO
        NightDTO nightDTO = new NightDTO();

        // Utilizamos el setter para establecer el valor
        nightDTO.setIconPhrase("Tormentas");

        // Verificamos que el getter devuelva el valor establecido
        assertThat(nightDTO.getIconPhrase()).isEqualTo("Tormentas");
    }

    @Test
    public void testJsonDeserialization() throws Exception {
        // Creamos un JSON que represente un objeto NightDTO
        String json = "{\"IconPhrase\":\"Llovizna\"}";

        // Utilizamos ObjectMapper para deserializar el JSON a un objeto NightDTO
        ObjectMapper objectMapper = new ObjectMapper();
        NightDTO nightDTO = objectMapper.readValue(json, NightDTO.class);

        // Verificamos que el campo se haya deserializado correctamente
        assertThat(nightDTO.getIconPhrase()).isEqualTo("Llovizna");
    }

}
