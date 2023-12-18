package com.provincia.challenge.locations.service;

import com.provincia.challenge.locations.client.AccuwheaterLocationsClientRest;
import com.provincia.challenge.locations.dto.CityDTO;
import com.provincia.challenge.locations.entity.City;
import com.provincia.challenge.mapper.Mapper;
import com.provincia.challenge.locations.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private AdministrativeAreaService administrativeAreaService;


    private final AccuwheaterLocationsClientRest accuwheaterLocationsClientRest;

    @Value("${api.key}")
    private String apiKey;

    @Value("${language}")
    private String language;

    @Override
    public ResponseEntity<?> listOfExternalApi(String countryCode, String adminCode, String q) {
        // Obtenemos las ciudades de la API de Accuweather
        List<CityDTO> citiesFromApi = accuwheaterLocationsClientRest.getCities(countryCode, adminCode, apiKey, q, language);
        return ResponseEntity.status(HttpStatus.OK).body(citiesFromApi);
    }

    @Override
    @Transactional(readOnly = true)
    public List<City> list() {
        return cityRepository.findAll();
    }

    @Override
    public boolean existsById(String id) {
        return cityRepository.existsById(id);
    }

    @Override
    @Transactional
    public List<City> saveAll(List<City> cities) {
        return cityRepository.saveAll(cities);
    }

    @Override
    @Transactional
    public ResponseEntity<?> saveCityIfNeeded(List<CityDTO> cityDTOList) {
        // Mapeamos la lista de tipo CityDTO a City
        List<City> citiesToSave = cityDTOList.stream()
                .map(cityDTO -> Mapper.mapCityDTOToCityEntity(cityDTO, administrativeAreaService))
                .collect(Collectors.toList());

        // Filtramos las ciudades que no existen en la base de datos
        List<City> citiesNotInDatabase = citiesToSave.stream()
                .filter(city -> !existsById(city.getId()))
                .collect(Collectors.toList());

        // Si se guardaron ciudades las devolvemos, en caso contrario devolvemos todas las guardadas en la base de datos
        if (!citiesNotInDatabase.isEmpty()) {
            List<City> savedCities = saveAll(citiesNotInDatabase);
            log.info("{} cities saved to the database.", savedCities.size());
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCities);
        } else {
            log.info("No new cities to save.");
            return ResponseEntity.status(HttpStatus.OK).body(list());
        }
    }
}
