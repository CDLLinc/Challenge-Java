package com.provincia.challenge.locations.client;

import com.provincia.challenge.locations.dto.AdministrativeAreaDTO;
import com.provincia.challenge.locations.dto.CityDTO;
import com.provincia.challenge.locations.dto.CountryDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "accuwheater-locations", url = "${url}/locations/v1")
public interface AccuwheaterLocationsClientRest {

    @GetMapping(value = "/countries", consumes = MediaType.APPLICATION_JSON_VALUE)
    List<CountryDTO> getCountries(@RequestParam("apikey") String apiKey, @RequestParam("language") String language);

    @GetMapping(value = "/cities/{countryCode}/{adminCode}/search", consumes = MediaType.APPLICATION_JSON_VALUE)
    List<CityDTO> getCities(@PathVariable("countryCode") String countryCode, @PathVariable("adminCode") String adminCode, @RequestParam("apikey") String apiKey, @RequestParam("q") String q, @RequestParam("language") String language);

    @GetMapping(value = "/adminareas/{countryCode}", consumes = MediaType.APPLICATION_JSON_VALUE)
    List<AdministrativeAreaDTO> getAdministrativeAreas(@PathVariable("countryCode") String countryCode, @RequestParam("apikey") String apiKey, @RequestParam("language") String language);
}
