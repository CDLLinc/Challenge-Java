package com.provincia.challenge.entities;

import com.provincia.challenge.locations.entity.Country;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountryTest {

    @Test
    public void testConstructorAndGetters() {
        // Instanciamos un país
        String id = "AR";
        String localizedName = "Argentina";
        Country country = new Country(id, localizedName);

        // Hacemos verificaciones
        assertEquals(id, country.getId());
        assertEquals(localizedName, country.getLocalizedName());
    }

    @Test
    public void testConstructorWithId() {
        // Definimos una variable con el id de un país
        String id = "AR";

        // Instanciamos un país a partir del id
        Country country = new Country(id);

        // Hacemos verificaciones
        assertEquals(id, country.getId());
    }

}

