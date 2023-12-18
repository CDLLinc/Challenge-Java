package com.provincia.challenge.entities;

import com.provincia.challenge.locations.entity.AdministrativeArea;
import com.provincia.challenge.locations.entity.Country;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdministrativeAreaTest {

    @Test
    public void testConstructorAndGetters() {
        // Definimos variables para un área administrativa
        String id = "B";
        String localizedName = "Buenos Aires";

        // Instanciamos un país
        Country country = new Country("AR", "Argentina");

        // Instanciamos un área administrativa
        AdministrativeArea administrativeArea = new AdministrativeArea(id, localizedName, country);

        // Hacemos verificaciones
        assertEquals(id, administrativeArea.getId());
        assertEquals(localizedName, administrativeArea.getLocalizedName());
        assertEquals(country, administrativeArea.getCountry());
    }

    @Test
    public void testConstructorWithId() {
        // Definimos una variable para el id de un área administrativa
        String id = "B";

        // Instanciamos el área administrativa a partir del id
        AdministrativeArea administrativeArea = new AdministrativeArea(id);

        // Verificamos
        assertEquals(id, administrativeArea.getId());
    }

}

