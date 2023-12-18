package com.provincia.challenge.entities;

import com.provincia.challenge.locations.entity.AdministrativeArea;
import com.provincia.challenge.locations.entity.City;
import com.provincia.challenge.locations.entity.Country;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CityTest {

    @Test
    public void testConstructorAndGetters() {
        // Instanciamos un país y un área administrativa
        String id = "1-2923_15_AL";
        String localizedName = "Junin";
        Country country = new Country("AR", "Argentina");
        AdministrativeArea administrativeArea = new AdministrativeArea("B", "Buenos Aires", country);

        // Instanciamos una ciudad
        City city = new City(id, localizedName, administrativeArea);

        // Hacemos verificaciones
        assertEquals(id, city.getId());
        assertEquals(localizedName, city.getLocalizedName());
        assertEquals(administrativeArea, city.getAdministrativeArea());
    }

}
