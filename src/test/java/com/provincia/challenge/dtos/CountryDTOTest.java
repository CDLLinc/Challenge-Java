package com.provincia.challenge.dtos;

import com.provincia.challenge.locations.dto.CountryDTO;
import org.junit.Test;
import static org.junit.Assert.*;

public class CountryDTOTest {

    @Test
    public void testConstructorAndGetters() {
        // Instanciamos un DTO de país
        String id = "AR";
        String localizedName = "Argentina";
        CountryDTO countryDTO = new CountryDTO(id, localizedName);

        // Hacemos verificaciones
        assertEquals(id, countryDTO.getId());
        assertEquals(localizedName, countryDTO.getLocalizedName());
    }

    @Test
    public void testEqualsAndHashCode() {
        // Instanciamos DTOs de países
        CountryDTO country1 = new CountryDTO("AR", "Argentina");
        CountryDTO country2 = new CountryDTO("AR", "Argentina");
        CountryDTO country3 = new CountryDTO("AF", "Afganistán");

        // Hacemos verificaciones
        assertEquals(country1, country2);
        assertNotEquals(country1, country3);
        assertEquals(country1.hashCode(), country2.hashCode());
        assertNotEquals(country1.hashCode(), country3.hashCode());
    }

}

