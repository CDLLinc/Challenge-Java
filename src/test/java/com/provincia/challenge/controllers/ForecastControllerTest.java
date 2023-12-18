package com.provincia.challenge.controllers;

import com.provincia.challenge.forecast.controller.ForecastController;
import com.provincia.challenge.forecast.dto.ForecastDTO;
import com.provincia.challenge.forecast.entity.Forecast;
import com.provincia.challenge.forecast.service.ForecastService;
import com.provincia.challenge.messages.Message;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(MockitoJUnitRunner.class)
public class ForecastControllerTest {

    private MockMvc mockMvc;
    @Mock
    private ForecastService forecastService;

    @InjectMocks
    private ForecastController forecastController;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(forecastController).build();
    }

    @Test
    public void testGetAndSave_Success() throws Exception {
        // Mock del servicio para simular una respuesta exitosa
        ForecastDTO mockForecastDTO = new ForecastDTO();
        doReturn(ResponseEntity.ok(mockForecastDTO)).when(forecastService).dayOfExternalApi(anyString());
        doReturn(ResponseEntity.ok(mockForecastDTO)).when(forecastService).saveForecastIfNeeded(anyString(), any());

        // Realizamos la llamada al método
        ResponseEntity<?> responseEntity = forecastController.getAndSave("someLocationKey");

        // Verificamos que la respuesta sea exitosa
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testGetAndSave_Error() throws Exception {
        // Mock del servicio para simular una respuesta de error
        Message errorMessage = new Message("Mensaje de error");
        doReturn(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage)).when(forecastService).dayOfExternalApi(anyString());

        // Realizamos la llamada al método
        ResponseEntity<?> responseEntity = forecastController.getAndSave("errorLocationKey");

        // Verificamos que la respuesta sea un error interno del servidor
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    @Test
    public void testGetForecasts() throws Exception {
        // Mock del servicio para simular una lista de pronósticos
        List<Forecast> forecastList = Collections.emptyList();
        when(forecastService.getForecast()).thenReturn(forecastList);

        // Realizamos la solicitud GET simulada
        mockMvc.perform(get("/api/forecasts"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    public void testGetByLocationKey() throws Exception {
        // Mock del servicio para simular una lista de pronósticos por clave de ubicación
        List<Forecast> forecastList = Collections.emptyList();
        when(forecastService.getByLocationKey(anyString())).thenReturn(forecastList);

        // Realizamos la solicitud GET simulada
        mockMvc.perform(get("/api/forecast/key/{key}", "someKey"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    public void testDeleteById_ExistingId() throws Exception {
        // Mock del servicio para simular un pronóstico existente
        Forecast mockForecast = new Forecast(); // Ajusta esto según tu implementación real
        when(forecastService.getOne(anyInt())).thenReturn(Optional.of(mockForecast));

        // Realizamos la solicitud DELETE simulada
        mockMvc.perform(delete("/api/forecast/delete/{id}", 1))
                .andExpect(status().isNoContent());

        // Verificamos que el método deleteById del servicio se haya llamado
        verify(forecastService, times(1)).deleteById(anyInt());
    }
}
