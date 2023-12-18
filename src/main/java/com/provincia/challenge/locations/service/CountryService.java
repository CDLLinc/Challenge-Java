package com.provincia.challenge.locations.service;

import com.provincia.challenge.locations.dto.CountryDTO;
import com.provincia.challenge.locations.entity.Country;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface CountryService {

    ResponseEntity<?> listOfExternalApi();
    List<Country> list();
    Optional<Country> getOne(String id);
    boolean existsById(String id);
    List<Country> saveAll(List<Country> countries);
    ResponseEntity<?> saveCountriesIfNeeded(List<CountryDTO> countryDTOList);
}
