package com.provincia.challenge.dtos;

import com.provincia.challenge.locations.dto.AdministrativeAreaDTO;
import com.provincia.challenge.locations.entity.Country;
import org.junit.Test;
import static org.junit.Assert.*;

public class AdministrativeAreaDTOTest {

    @Test
    public void testConstructorAndGetters() {
        // Instanciamos el país
        String id = "B";
        String localizedName = "Buenos Aires";
        Country country = new Country("AR", "Argentina");

        // Creamos un DTO del área administrativa
        AdministrativeAreaDTO administrativeAreaDTO = new AdministrativeAreaDTO(id, localizedName, country);

        // Hacemos verificaciones
        assertEquals(id, administrativeAreaDTO.getId());
        assertEquals(localizedName, administrativeAreaDTO.getLocalizedName());
        assertEquals(country, administrativeAreaDTO.getCountry());
    }

    @Test
    public void testEqualsAndHashCode() {
        // Instanciamos áreas administrativas
        AdministrativeAreaDTO area1 = new AdministrativeAreaDTO("A", "Salta", new Country("AR", "Argentina"));
        AdministrativeAreaDTO area2 = new AdministrativeAreaDTO("A", "Salta", new Country("AR", "Argentina"));
        AdministrativeAreaDTO area3 = new AdministrativeAreaDTO("B", "Buenos Aires", new Country("AR", "Argentina"));

        // Hacemos verificaciones
        assertEquals(area1, area2);
        assertNotEquals(area1, area3);
        assertEquals(area1.hashCode(), area2.hashCode());
        assertNotEquals(area1.hashCode(), area3.hashCode());
    }
}

