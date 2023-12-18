package com.provincia.challenge.services;

import com.provincia.challenge.locations.client.AccuwheaterLocationsClientRest;
import com.provincia.challenge.locations.dto.CountryDTO;
import com.provincia.challenge.locations.service.CountryServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CountryServiceTest {

    @Mock
    private AccuwheaterLocationsClientRest accuwheaterLocationsClientRest;

    @InjectMocks
    private CountryServiceImpl countryService;

    @Value("${api.key}")
    private String apiKey;

    @Value("${language}")
    private String language;

    @Test
    public void testListOfExternalApi() {
        // Configuramos el mock para el servicio externo
        List<CountryDTO> mockCountries = Arrays.asList(new CountryDTO("AF", "Afganistán"));
        when(accuwheaterLocationsClientRest.getCountries(apiKey, language)).thenReturn(mockCountries);

        // Ejecutamos el método bajo prueba
        ResponseEntity<?> responseEntity = countryService.listOfExternalApi();

        // Verificamos que se devuelva el resultado esperado
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockCountries, responseEntity.getBody());
    }
}