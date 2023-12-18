package com.provincia.challenge.locations.service;

import com.provincia.challenge.locations.client.AccuwheaterLocationsClientRest;
import com.provincia.challenge.locations.dto.AdministrativeAreaDTO;
import com.provincia.challenge.locations.entity.AdministrativeArea;
import com.provincia.challenge.mapper.Mapper;
import com.provincia.challenge.locations.repository.AdministrativeAreaRepository;
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
public class AdministrativeAreaServiceImpl implements AdministrativeAreaService {

    @Autowired
    private AdministrativeAreaRepository administrativeAreaRepository;

    private final AccuwheaterLocationsClientRest accuwheaterLocationsClientRest;

    @Value("${api.key}")
    private String apiKey;

    @Value("${language}")
    private String language;

    @Override
    public ResponseEntity<?> listOfExternalApi(String countryCode) {
        // Obtenemos las áreas administrativas de la API de Accuweather
        List<AdministrativeAreaDTO> administrativeAreasFromApi = accuwheaterLocationsClientRest.getAdministrativeAreas(countryCode, apiKey, language);
        return ResponseEntity.status(HttpStatus.OK).body(administrativeAreasFromApi);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AdministrativeArea> list() {
        return administrativeAreaRepository.findAll();
    }

    @Override
    public Optional<AdministrativeArea> getOne(String id) {
        return administrativeAreaRepository.findById(id);
    }

    @Override
    public boolean existsById(String id) {
        return administrativeAreaRepository.existsById(id);
    }

    @Override
    @Transactional
    public List<AdministrativeArea> saveAll(List<AdministrativeArea> administrativeAreas) {
        return administrativeAreaRepository.saveAll(administrativeAreas);
    }

    @Override
    @Transactional
    public ResponseEntity<?> saveAdministrativeAreaIfNeeded(List<AdministrativeAreaDTO> administrativeAreaDTO) {
        // Mapeamos la lista de tipo AdministrativeAreaDTO a AdministrativeArea
        List<AdministrativeArea> administrativeAreasToSave = administrativeAreaDTO.stream()
                .map(Mapper::mapAdministrativeAreaDTOToAdministrativeAreaEntity)
                .collect(Collectors.toList());

        // Filtramos las áreas administrativas que no existen en la base de datos
        List<AdministrativeArea> administrativeAreasNotInDatabase = administrativeAreasToSave.stream()
                .filter(administrativeArea -> !existsById(administrativeArea.getId()))
                .collect(Collectors.toList());

        // Si se guardaron áreas administrativas las devolvemos, en caso contrario devolvemos todas las guardadas en la base de datos
        if (!administrativeAreasNotInDatabase.isEmpty()) {
            List<AdministrativeArea> savedAdministrativeAreas = saveAll(administrativeAreasNotInDatabase);
            log.info("{} administrative areas saved to the database.", savedAdministrativeAreas.size());
            return ResponseEntity.status(HttpStatus.CREATED).body(savedAdministrativeAreas);
        } else {
            log.info("No new administrative areas to save.");
            return ResponseEntity.status(HttpStatus.OK).body(list());
        }
    }
}
