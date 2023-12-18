package com.provincia.challenge.locations.controller;

import com.provincia.challenge.locations.dto.CountryDTO;
import com.provincia.challenge.locations.service.CountryService;
import com.provincia.challenge.messages.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
@CrossOrigin()
@Api(tags = "Controlador de Países")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @ApiOperation(value = "Obtener una lista de países"
            ,notes = "Se obtiene una lista de países")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "El recurso se obtiene correctamente de la API externa y se guarda en la base de datos"),
            @ApiResponse(code = 200, message = "El recurso ya existe en la base de datos, se consulta desde ahí"),
            @ApiResponse(code = 404, message = "El endpoint solicitado no existe"),
            @ApiResponse(code = 500, message = "Ocurrió un error interno en el servidor")})
    @GetMapping("/countries")
    public ResponseEntity<?> listAndSave() {

        // Obtenemos los países de la api de Accuweather
        ResponseEntity<?> response = countryService.listOfExternalApi();

        // Manejamos lo que pasa, en caso que la respuesta sea un objeto de tipo Message
        if (response.getBody() instanceof Message) {
            Message errorMessage = (Message) response.getBody();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }

        // Guardamos los países si es que no están guardados
        return countryService.saveCountriesIfNeeded((List<CountryDTO>) response.getBody());
    }

}
