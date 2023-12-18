package com.provincia.challenge.locations.service;
import com.provincia.challenge.locations.dto.CityDTO;
import com.provincia.challenge.locations.entity.City;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CityService {

    ResponseEntity<?> listOfExternalApi(String countryCode, String adminCode, String q);
    List<City> list();
    boolean existsById(String id);
    List<City> saveAll(List<City> cities);
    ResponseEntity<?> saveCityIfNeeded(List<CityDTO> cities);
}
