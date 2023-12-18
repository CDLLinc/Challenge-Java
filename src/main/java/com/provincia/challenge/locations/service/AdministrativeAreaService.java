package com.provincia.challenge.locations.service;

import com.provincia.challenge.locations.dto.AdministrativeAreaDTO;
import com.provincia.challenge.locations.entity.AdministrativeArea;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface AdministrativeAreaService {

    ResponseEntity<?> listOfExternalApi(String countryCode);
    List<AdministrativeArea> list();
    Optional<AdministrativeArea> getOne(String id);
    boolean existsById(String id);
    List<AdministrativeArea> saveAll(List<AdministrativeArea> administrativeAreas);
    ResponseEntity<?> saveAdministrativeAreaIfNeeded(List<AdministrativeAreaDTO> administrativeAreaDTO);
}
