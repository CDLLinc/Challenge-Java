package com.provincia.challenge.formatter;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeConverter {

    public static LocalDateTime convertStringToLocalDateTime(String dateString) {
        // Defininimos un formateador para el patrón ISO 8601
        DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

        // Parseamos la fecha a OffsetDateTime
        OffsetDateTime offsetDateTime = OffsetDateTime.parse(dateString, formatter);

        // Convertimos el OffsetDateTime a LocalDateTime
        return offsetDateTime.toLocalDateTime();
    }
}
