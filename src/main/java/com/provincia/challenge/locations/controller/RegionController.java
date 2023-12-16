package com.provincia.challenge.locations.controller;

import com.provincia.challenge.locations.dto.RegionDTO;
import com.provincia.challenge.locations.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
@CrossOrigin()
public class RegionController {

    @Autowired
    private RegionService regionService;

    @GetMapping("/regions")
    public ResponseEntity<?> listAndSave() {
        ResponseEntity<?> response = regionService.listOfExternalApi();
        return regionService.saveRegionsIfNeeded((List<RegionDTO>) response.getBody());
    }

}
