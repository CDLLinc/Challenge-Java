package com.provincia.challenge.locations.controller;

import com.provincia.challenge.locations.dto.RegionDTO;
import com.provincia.challenge.locations.service.RegionService;
import com.provincia.challenge.messages.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

        // Obtenemos las regiones de la api de Accuweather
        ResponseEntity<?> response = regionService.listOfExternalApi();

        // Manejamos lo que pasa, en caso que la respuesta sea un objeto de tipo Message
        if (response.getBody() instanceof Message) {
            Message errorMessage = (Message) response.getBody();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }

        // Guardamos las regiones si es que no están guardadas
        return regionService.saveRegionsIfNeeded((List<RegionDTO>) response.getBody());
    }

}
