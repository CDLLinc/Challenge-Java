package com.provincia.challenge.mapper;

import com.provincia.challenge.forecast.dto.*;
import com.provincia.challenge.forecast.entity.Forecast;
import com.provincia.challenge.locations.dto.AdministrativeAreaDTO;
import com.provincia.challenge.locations.dto.CountryDTO;
import com.provincia.challenge.locations.entity.AdministrativeArea;
import com.provincia.challenge.locations.entity.Country;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MapperTest {

    @Test
    public void testMapCountryDTOToCountryEntity() {

        // Instanciamos un DTO de país
        CountryDTO countryDTO = new CountryDTO("AR", "Argentina");

        // Lo mapeamos a la entidad Country
        Country country = Mapper.mapCountryDTOToCountryEntity(countryDTO);

        // Hacemos verificaciones
        assertEquals("AR", country.getId());
        assertEquals("Argentina", country.getLocalizedName());
    }


    @Test
    public void testMapAdministrativeAreaDTOToAdministrativeAreaEntity() {

        // Instanciamos un país y un DTO de área administrativa
        Country country = new Country("AR", "Argentina");
        AdministrativeAreaDTO administrativeAreaDTO = new AdministrativeAreaDTO("B", "Buenos Aires", country);

        // Mapeamos el DTO del área administrativa a la entidad
        AdministrativeArea administrativeArea = Mapper.mapAdministrativeAreaDTOToAdministrativeAreaEntity(administrativeAreaDTO);

        // Hacemos verificaciones
        assertEquals("B", administrativeArea.getId());
        assertEquals("Buenos Aires", administrativeArea.getLocalizedName());
        assertEquals(country, administrativeArea.getCountry());
    }

    @Test
    public void testMapForecastDTOToForecastEntity() {
        // Instanciamos un DTO de Forecast con todo lo que implica
        String locationKey = "7414";
        HeadlineDTO headlineDTO = new HeadlineDTO("2023-12-18T13:00:00-03:00", "2023-12-18T19:00:00-03:00", "Previsión de chaparrones el lunes por la tarde", 5);
        TemperatureValueDTO min = new TemperatureValueDTO(20, "F");
        TemperatureValueDTO max = new TemperatureValueDTO(40, "F");
        TemperatureDTO temperatureDTO = new TemperatureDTO(min, max);
        DayDTO dayDTO = new DayDTO("Llovizna");
        NightDTO nightDTO = new NightDTO("Tormentas");
        DailyForecastDTO dailyForecastDTO = new DailyForecastDTO(temperatureDTO, dayDTO, nightDTO);
        List<DailyForecastDTO> listDailyForecast = new ArrayList<>();
        listDailyForecast.add(dailyForecastDTO);
        ForecastDTO forecastDTO = new ForecastDTO(headlineDTO, listDailyForecast);

        // Mapeamos el DTO a la entidad
        Forecast forecast = Mapper.mapForecastDTOToForecastEntity(locationKey, forecastDTO);

        // Hacemos verificaciones
        assertEquals(locationKey, forecast.getLocationKey());
        assertEquals(LocalDateTime.of(2023, 12, 18, 13, 0), forecast.getEffectiveDate());
        assertEquals(LocalDateTime.of(2023, 12, 18, 19, 0), forecast.getEndDate());
        assertEquals("Previsión de chaparrones el lunes por la tarde", forecast.getText());
        assertEquals(5, forecast.getSeverity());
        assertEquals(20, forecast.getMinTemperature());
        assertEquals(40, forecast.getMaxTemperature());
        assertEquals("F", forecast.getUnitTemperature());
        assertEquals("Llovizna", forecast.getDayIcon());
        assertEquals("Tormentas", forecast.getNightIcon());
    }
}
