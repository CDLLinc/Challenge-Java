package com.provincia.challenge.mapper;

import com.provincia.challenge.forecast.dto.ForecastDTO;
import com.provincia.challenge.forecast.entity.Forecast;
import com.provincia.challenge.formatter.DateTimeConverter;
import com.provincia.challenge.locations.dto.AdministrativeAreaDTO;
import com.provincia.challenge.locations.dto.CityDTO;
import com.provincia.challenge.locations.dto.CountryDTO;
import com.provincia.challenge.locations.entity.AdministrativeArea;
import com.provincia.challenge.locations.entity.City;
import com.provincia.challenge.locations.entity.Country;
import com.provincia.challenge.locations.service.AdministrativeAreaService;
public class Mapper {

    public static Country mapCountryDTOToCountryEntity(CountryDTO countryDTO) {
        return new Country(
                countryDTO.getId(),
                countryDTO.getLocalizedName()
                );
    }

    public static City mapCityDTOToCityEntity(CityDTO cityDTO, AdministrativeAreaService administrativeAreaService) {
        return new City(
                cityDTO.getId(),
                cityDTO.getLocalizedName(),
                administrativeAreaService.getOne(cityDTO.getAdministrativeArea().getId()).get()
        );
    }

    public static AdministrativeArea mapAdministrativeAreaDTOToAdministrativeAreaEntity(AdministrativeAreaDTO administrativeAreaDTO) {
        return new AdministrativeArea(
                administrativeAreaDTO.getId(),
                administrativeAreaDTO.getLocalizedName(),
                administrativeAreaDTO.getCountry()
        );
    }

    public static Forecast mapForecastDTOToForecastEntity(String locationkey, ForecastDTO forecastDTO) {
        return new Forecast(
                0,
                locationkey,
                DateTimeConverter.convertStringToLocalDateTime(forecastDTO.getHeadline().getEffectiveDate()),
                DateTimeConverter.convertStringToLocalDateTime(forecastDTO.getHeadline().getEndDate()),
                forecastDTO.getHeadline().getText(),
                forecastDTO.getHeadline().getSeverity(),
                forecastDTO.getDailyForecasts().get(0).getTemperature().getMinimum().getValue(),
                forecastDTO.getDailyForecasts().get(0).getTemperature().getMaximum().getValue(),
                forecastDTO.getDailyForecasts().get(0).getTemperature().getMinimum().getUnit(),
                forecastDTO.getDailyForecasts().get(0).getDay().getIconPhrase(),
                forecastDTO.getDailyForecasts().get(0).getNight().getIconPhrase()
        );
    }
}
