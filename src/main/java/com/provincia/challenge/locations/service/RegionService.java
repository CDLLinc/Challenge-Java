package com.provincia.challenge.locations.service;

import com.provincia.challenge.locations.dto.RegionDTO;
import com.provincia.challenge.locations.entity.Region;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RegionService {

    ResponseEntity<?> listOfExternalApi();
    List<Region> list();
    boolean existsById(String id);
    List<Region> saveAll(List<Region> regions);
    ResponseEntity<?> saveRegionsIfNeeded(List<RegionDTO> regions);
}
