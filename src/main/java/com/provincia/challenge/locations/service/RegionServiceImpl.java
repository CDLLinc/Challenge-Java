package com.provincia.challenge.locations.service;

import com.provincia.challenge.locations.dto.RegionDTO;
import com.provincia.challenge.locations.clients.AccuwheaterClientRest;
import com.provincia.challenge.locations.entity.Region;
import com.provincia.challenge.locations.mappers.RegionMapper;
import com.provincia.challenge.locations.repository.RegionRepository;
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
public class RegionServiceImpl implements RegionService {

    @Autowired
    private RegionRepository regionRepository;

    private final AccuwheaterClientRest accuwheaterClientRest;

    @Value("${accuwheater.api.key}")
    private String apiKey;

    @Value("${accuwheater.language}")
    private String language;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<?> listOfExternalApi(){
        // Obtenemos las regiones de la API de Accuweather
        List<RegionDTO> regionsFromApi = accuwheaterClientRest.getRegions(apiKey, language);
        return ResponseEntity.status(HttpStatus.OK).body(regionsFromApi);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Region> list() {
        return regionRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(String id){
        return regionRepository.existsById(id);
    }

    @Override
    @Transactional
    public List<Region> saveAll(List<Region> regions){
        return regionRepository.saveAll(regions);
    }

    @Override
    @Transactional
    public ResponseEntity<?> saveRegionsIfNeeded(List<RegionDTO> regionDTOList) {

        // Mapeamos la lista de tipo RegionDTO a Region
        List<Region> regionsToSave = regionDTOList.stream()
                .map(RegionMapper::mapRegionDTOToRegionEntity)
                .collect(Collectors.toList());

        // Filtramos las regiones que no existen en la base de datos
        List<Region> regionsNotInDatabase = regionsToSave.stream()
                .filter(region -> !existsById(region.getId()))
                .collect(Collectors.toList());

        // Si se guardaron regiones las devolvemos, en caso contrario devolvemos todas las guardadas en la base de datos
        if (!regionsNotInDatabase.isEmpty()) {
            List<Region> savedRegions = saveAll(regionsNotInDatabase);
            log.info("{} regions saved to the database.", savedRegions.size());
            return ResponseEntity.status(HttpStatus.CREATED).body(savedRegions);
        } else {
            log.info("No new regions to save.");
            return ResponseEntity.status(HttpStatus.OK).body(list());
        }
    }
}
