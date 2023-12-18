package com.provincia.challenge.locations.controller;

import com.provincia.challenge.locations.dto.CityDTO;
import com.provincia.challenge.locations.service.CityService;
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
@Api(tags = "Controlador de Ciudades")
public class CitiesController {

    @Autowired
    private CityService cityService;

    @ApiOperation(value = "Obtener una lista de ciudades a partir del código de un país, el código del área administrativa y el nombre de la ciudad"
            ,notes = "Se obtiene una lista de ciudades a partir del código de un país, el código del área administrativa y el nombre de la ciudad")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "El recurso se obtiene correctamente de la API externa y se guarda en la base de datos"),
            @ApiResponse(code = 200, message = "El recurso ya existe en la base de datos, se consulta desde ahí"),
            @ApiResponse(code = 404, message = "El endpoint solicitado no existe"),
            @ApiResponse(code = 500, message = "Ocurrió un error interno en el servidor")})
    @GetMapping("/city/{countryCode}/{adminCode}/{search}")
    public ResponseEntity<?> searchAndSave(@PathVariable("countryCode") String countryCode, @PathVariable("adminCode") String adminCode, @PathVariable("search") String search) {

        // Obtenemos las ciudades de la api de Accuweather
        ResponseEntity<?> response = cityService.listOfExternalApi(countryCode, adminCode, search);

        List<CityDTO> list = (List<CityDTO>) response.getBody();
        System.out.println("Cantidad de elementos en la lista " + list.size());

        // Manejamos lo que pasa, en caso que la respuesta sea un objeto de tipo Message
        if (response.getBody() instanceof Message) {
            Message errorMessage = (Message) response.getBody();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }

        // Guardamos las ciudades si es que no están guardadas
        return cityService.saveCityIfNeeded((List<CityDTO>) response.getBody());
    }
}
