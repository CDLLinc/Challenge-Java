package com.provincia.challenge.dtos;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.provincia.challenge.forecast.dto.HeadlineDTO;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HeadlineDTOTest {

    @Test
    public void testAllArgsConstructor() {
        // Creamos una instancia de HeadlineDTO usando el constructor con argumentos
        HeadlineDTO headlineDTO = new HeadlineDTO("2023-12-18T13:00:00-03:00", "2023-12-18T19:00:00-03:00", "Previsión de chaparrones el lunes por la tarde", 5);

        // Verificamos que los campos se hayan inicializado correctamente
        assertThat(headlineDTO.getEffectiveDate()).isEqualTo("2023-12-18T13:00:00-03:00");
        assertThat(headlineDTO.getEndDate()).isEqualTo("2023-12-18T19:00:00-03:00");
        assertThat(headlineDTO.getText()).isEqualTo("Previsión de chaparrones el lunes por la tarde");
        assertThat(headlineDTO.getSeverity()).isEqualTo(5);
    }

    @Test
    public void testNoArgsConstructor() {
        // Creamos una instancia de HeadlineDTO usando el constructor sin argumentos
        HeadlineDTO headlineDTO = new HeadlineDTO();

        // Verificamos que los campos sean nulos o tengan los valores predeterminados según sea el caso
        assertThat(headlineDTO.getEffectiveDate()).isNull();
        assertThat(headlineDTO.getEndDate()).isNull();
        assertThat(headlineDTO.getText()).isNull();
        assertThat(headlineDTO.getSeverity()).isEqualTo(0);
    }

    @Test
    public void testSettersAndGetters() {
        // Creamos una instancia de HeadlineDTO
        HeadlineDTO headlineDTO = new HeadlineDTO();

        // Utilizamos setters para establecer los valores
        headlineDTO.setEffectiveDate("2023-12-18T13:00:00-03:00");
        headlineDTO.setEndDate("2023-12-18T19:00:00-03:00");
        headlineDTO.setText("Previsión de chaparrones el lunes por la tarde");
        headlineDTO.setSeverity(5);

        // Verificamos que los getters devuelvan los valores establecidos
        assertThat(headlineDTO.getEffectiveDate()).isEqualTo("2023-12-18T13:00:00-03:00");
        assertThat(headlineDTO.getEndDate()).isEqualTo("2023-12-18T19:00:00-03:00");
        assertThat(headlineDTO.getText()).isEqualTo("Previsión de chaparrones el lunes por la tarde");
        assertThat(headlineDTO.getSeverity()).isEqualTo(5);
    }

    @Test
    public void testJsonDeserialization() throws Exception {
        // Creamos un JSON que represente un objeto HeadlineDTO
        String json = "{\"EffectiveDate\":\"2023-12-18T13:00:00-03:00\",\"EndDate\":\"2023-12-18T19:00:00-03:00\",\"Text\":\"Previsión de chaparrones el lunes por la tarde\",\"Severity\":5}";

        // Utilizamos ObjectMapper para deserializar el JSON a un objeto HeadlineDTO
        ObjectMapper objectMapper = new ObjectMapper();
        HeadlineDTO headlineDTO = objectMapper.readValue(json, HeadlineDTO.class);

        // Verificamos que los campos se hayan deserializado correctamente
        assertThat(headlineDTO.getEffectiveDate()).isEqualTo("2023-12-18T13:00:00-03:00");
        assertThat(headlineDTO.getEndDate()).isEqualTo("2023-12-18T19:00:00-03:00");
        assertThat(headlineDTO.getText()).isEqualTo("Previsión de chaparrones el lunes por la tarde");
        assertThat(headlineDTO.getSeverity()).isEqualTo(5);
    }
}
