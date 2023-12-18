package com.provincia.challenge.forecast.controller;

import com.provincia.challenge.forecast.dto.ForecastDTO;
import com.provincia.challenge.forecast.entity.Forecast;
import com.provincia.challenge.forecast.service.ForecastService;
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
import java.util.Optional;


@RestController
@RequestMapping("/api")
@CrossOrigin()
@Api(tags = "Controlador del Clima")
public class ForecastController {

    @Autowired
    private ForecastService forecastService;

    @ApiOperation(value = "Obtener y guardar el clima de una ciudad a partir de la clave de la ciudad (locationKey)"
            ,notes = "Se obtiene y guarda el clima de una ciudad a partir de la clave de la ciudad (locationKey)")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "El recurso se obtiene correctamente de la API externa y se guarda en la base de datos"),
            @ApiResponse(code = 200, message = "El recurso ya existe en la base de datos, se consulta desde ahí"),
            @ApiResponse(code = 404, message = "El endpoint solicitado no existe"),
            @ApiResponse(code = 500, message = "Ocurrió un error interno en el servidor")})
    @GetMapping("/forecast/{locationKey}")
    public ResponseEntity<?> getAndSave(@PathVariable("locationKey") String locationKey) {

        // Obtenemos el clima de la api de Accuweather
        ResponseEntity<?> response = forecastService.dayOfExternalApi(locationKey);

        // Manejamos lo que pasa, en caso que la respuesta sea un objeto de tipo Message
        if (response.getBody() instanceof Message) {
            Message errorMessage = (Message) response.getBody();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }

        return forecastService.saveForecastIfNeeded(locationKey, (ForecastDTO) response.getBody());
    }

    @ApiOperation(value = "Obtener todos los registros de clima guardados en la base de datos"
            ,notes = "Se obtienen todos los registros de clima guardados en la base de datos")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "El recurso se obtiene correctamente"),
            @ApiResponse(code = 404, message = "El endpoint solicitado no existe"),
            @ApiResponse(code = 500, message = "Ocurrió un error interno en el servidor")})
    @GetMapping("/forecasts")
    public List<Forecast> getForecasts(){
        return forecastService.getForecast();
    }

    @ApiOperation(value = "Obtener un registro del clima por id"
            ,notes = "Se obtienen un registro del clima a través de su id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "El recurso se obtiene correctamente"),
            @ApiResponse(code = 204, message = "El recurso no existe en la base de datos"),
            @ApiResponse(code = 404, message = "El endpoint solicitado no existe")})
    @GetMapping("/forecast/id/{id}")
    public ResponseEntity<Forecast> getById(@PathVariable("id") int id){
        if(!forecastService.existsById(id))
            return new ResponseEntity(new Message("No existe un registro con ese id."), HttpStatus.NOT_FOUND);
        return new ResponseEntity(forecastService.getOne(id).get(), HttpStatus.OK);
    }

    @ApiOperation(value = "Obtener un listado de registros del clima para una ciudad por la clave de la ciudad (locationKey)"
            ,notes = "Se obtiene un listado de registros del clima para una ciudad por la clave de la ciudad (locationKey)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "El recurso se obtiene correctamente"),
            @ApiResponse(code = 204, message = "El recurso no existe en la base de datos"),
            @ApiResponse(code = 404, message = "El endpoint solicitado no existe")})
    @GetMapping("/forecast/key/{key}")
    public List<Forecast> getByLocationKey(@PathVariable("key") String key){
        return forecastService.getByLocationKey(key);
    }

    @ApiOperation(value = "Eliminar un registro del clima por id"
            ,notes = "Se elimina un registro del clima a través de su id")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "El recurso se eliminó correctamente"),
            @ApiResponse(code = 404, message = "El endpoint solicitado no existe")})
    @DeleteMapping("/forecast/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable int id) {
        Optional<Forecast> forecast = forecastService.getOne(id);
        if (forecast.isPresent()){
            forecastService.deleteById(forecast.get().getId());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
