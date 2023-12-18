package com.provincia.challenge.formatter;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class DateTimeConverterTest {

    @Test
    void convertStringToLocalDateTime() {
        // Definimos una variable con fecha y hora en formato ISO 8601
        String dateString = "2023-12-17T15:30:00Z";

        // Llamamos al método
        LocalDateTime localDateTime = DateTimeConverter.convertStringToLocalDateTime(dateString);

        // Verificamos el resultado
        assertThat(localDateTime).isNotNull();
        assertThat(localDateTime.getYear()).isEqualTo(2023);
        assertThat(localDateTime.getMonthValue()).isEqualTo(12);
        assertThat(localDateTime.getDayOfMonth()).isEqualTo(17);
        assertThat(localDateTime.getHour()).isEqualTo(15);
        assertThat(localDateTime.getMinute()).isEqualTo(30);
        assertThat(localDateTime.getSecond()).isEqualTo(0);
    }

}
