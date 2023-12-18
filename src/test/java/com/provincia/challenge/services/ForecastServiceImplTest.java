package com.provincia.challenge.services;

import com.provincia.challenge.forecast.client.AccuweatherForecastClientRest;
import com.provincia.challenge.forecast.dto.ForecastDTO;
import com.provincia.challenge.forecast.service.ForecastService;
import com.provincia.challenge.forecast.service.ForecastServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class ForecastServiceImplTest {
    private AccuweatherForecastClientRest accuweatherForecastClientRest = mock(AccuweatherForecastClientRest.class);
    private ForecastService forecastService = new ForecastServiceImpl(accuweatherForecastClientRest);

    @Value("${api.key}")
    private String apiKey;

    @Value("${language}")
    private String language;

    private String locationKey = "7414";

    @Test
    void dayOfExternalApi() {
        // Configuramos el mock de AccuweatherForecastClientRest
        when(accuweatherForecastClientRest.getForecast(locationKey, apiKey, language))
                .thenReturn(new ForecastDTO());

        // Llamamos al método que se está probando
        ResponseEntity<?> responseEntity = forecastService.dayOfExternalApi(locationKey);

        // Verificamos el resultado
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
