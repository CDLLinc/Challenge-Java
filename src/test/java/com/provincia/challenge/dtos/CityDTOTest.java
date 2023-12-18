package com.provincia.challenge.dtos;

import com.provincia.challenge.locations.dto.AdministrativeAreaDTO;
import com.provincia.challenge.locations.dto.CityDTO;
import com.provincia.challenge.locations.entity.Country;
import org.junit.Test;
import static org.junit.Assert.*;

public class CityDTOTest {

    @Test
    public void testConstructorAndGetters() {
        // Instanciamos un DTO de área administrativa
        String id = "7414";
        String localizedName = "Lincoln";
        AdministrativeAreaDTO administrativeArea = new AdministrativeAreaDTO("B", "Buenos Aires", new Country("AR", "Argentina"));

        // Instanciamos un DTO de ciudad
        CityDTO cityDTO = new CityDTO(id, localizedName, administrativeArea);

        // Hacemos verificaciones
        assertEquals(id, cityDTO.getId());
        assertEquals(localizedName, cityDTO.getLocalizedName());
        assertEquals(administrativeArea, cityDTO.getAdministrativeArea());
    }

    @Test
    public void testEqualsAndHashCode() {
        // Instanciamos un DTO de área administrativa
        AdministrativeAreaDTO administrativeArea = new AdministrativeAreaDTO("B", "Buenos Aires", new Country("AR", "Argentina"));
        CityDTO city1 = new CityDTO("7414", "Lincoln", administrativeArea);
        CityDTO city2 = new CityDTO("1-2923_15_AL", "Junin", administrativeArea);
        CityDTO city3 = new CityDTO("7393", "Chacabuco", administrativeArea);

        // Hacemos verificaciones
        assertEquals(city1, city2);
        assertNotEquals(city1, city3);
        assertEquals(city1.hashCode(), city2.hashCode());
        assertNotEquals(city1.hashCode(), city3.hashCode());
    }

}
