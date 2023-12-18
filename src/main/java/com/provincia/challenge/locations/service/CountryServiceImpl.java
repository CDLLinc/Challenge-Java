package com.provincia.challenge.locations.service;

import com.provincia.challenge.locations.dto.CountryDTO;
import com.provincia.challenge.locations.client.AccuwheaterLocationsClientRest;
import com.provincia.challenge.locations.entity.Country;
import com.provincia.challenge.mapper.Mapper;
import com.provincia.challenge.locations.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryRepository countryRepository;

    private final AccuwheaterLocationsClientRest accuwheaterLocationsClientRest;

    @Value("${api.key}")
    private String apiKey;

    @Value("${language}")
    private String language;

    @Override
    public ResponseEntity<?> listOfExternalApi(){
        // Obtenemos los países de la API de Accuweather
        List<CountryDTO> countriesFromApi = accuwheaterLocationsClientRest.getCountries(apiKey, language);
        return ResponseEntity.status(HttpStatus.OK).body(countriesFromApi);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Country> list() {
        return countryRepository.findAll();
    }

    @Override
    public Optional<Country> getOne(String id) {
        return countryRepository.findById(id);
    }

    @Override
    public boolean existsById(String id){
        return countryRepository.existsById(id);
    }

    @Override
    @Transactional
    public List<Country> saveAll(List<Country> countries){
        return countryRepository.saveAll(countries);
    }

    @Override
    @Transactional
    public ResponseEntity<?> saveCountriesIfNeeded(List<CountryDTO> countryDTOList) {

        // Mapeamos la lista de tipo CountryDTO a Country
        List<Country> countriesToSave = countryDTOList.stream()
                .map(Mapper::mapCountryDTOToCountryEntity)
                .collect(Collectors.toList());

        // Filtramos los países que no existen en la base de datos
        List<Country> countriesNotInDatabase = countriesToSave.stream()
                .filter(country -> !existsById(country.getId()))
                .collect(Collectors.toList());

        // Si se guardaron países los devolvemos, en caso contrario devolvemos todos las guardados en la base de datos
        if (!countriesNotInDatabase.isEmpty()) {
            List<Country> savedCountries = saveAll(countriesNotInDatabase);
            log.info("{} countries saved to the database.", savedCountries.size());
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCountries);
        } else {
            log.info("No new countries to save.");
            return ResponseEntity.status(HttpStatus.OK).body(list());
        }
    }
}
