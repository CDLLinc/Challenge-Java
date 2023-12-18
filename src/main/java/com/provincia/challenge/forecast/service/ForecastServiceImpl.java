package com.provincia.challenge.forecast.service;

import com.provincia.challenge.forecast.client.AccuweatherForecastClientRest;
import com.provincia.challenge.forecast.dto.ForecastDTO;
import com.provincia.challenge.forecast.entity.Forecast;
import com.provincia.challenge.forecast.repository.ForecastRepository;
import com.provincia.challenge.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ForecastServiceImpl implements ForecastService{

    @Autowired
    private ForecastRepository forecastRepository;

    private final AccuweatherForecastClientRest accuweatherForecastClientRest;

    @Value("${api.key}")
    private String apiKey;

    @Value("${language}")
    private String language;

    @Override
    public ResponseEntity<?> dayOfExternalApi(String locationKey) {
        // Obtenemos el clima de la API de Accuweather
        ForecastDTO countriesFromApi = accuweatherForecastClientRest.getForecast(locationKey, apiKey, language);
        return ResponseEntity.status(HttpStatus.OK).body(countriesFromApi);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Forecast> list() {
        return forecastRepository.findAll();
    }

    @Override
    public boolean existsByLocationKeyAndEffectiveDate(String key, LocalDateTime effectiveDate) {
        return forecastRepository.existsByLocationKeyAndEffectiveDate(key, effectiveDate);
    }

    @Override
    public boolean existsById(int id) {
        return forecastRepository.existsById(id);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        forecastRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Forecast> getOne(int id) {
        return forecastRepository.findById(id);
    }

    @Override
    public List<Forecast> getForecast() {
        return forecastRepository.findAll();
    }

    @Override
    public Optional<Forecast> getByLocationKeyAndEffectiveDate(String locationKey, LocalDateTime effectiveDate) {
        return forecastRepository.getByLocationKeyAndEffectiveDate(locationKey, effectiveDate);
    }

    @Override
    public List<Forecast> getByLocationKey(String locationKey) {
        return forecastRepository.getByLocationKey(locationKey);
    }

    @Override
    @Transactional
    public Forecast save(Forecast forecast) {
        return forecastRepository.save(forecast);
    }

    @Override
    @Transactional
    public ResponseEntity<?> saveForecastIfNeeded(String locationKey, ForecastDTO forecastDTO) {

        // Mapeamos el clima de tipo ForecastDTO a Forecast
        Forecast forecastToSave = Mapper.mapForecastDTOToForecastEntity(locationKey, forecastDTO);

        //Validamos si existe por la clave de la localidad y la fecha del pronóstico, si no existe lo guardamos y lo devolvemos. Si existe devolvemos el ya guardado.
        if (!existsByLocationKeyAndEffectiveDate(forecastToSave.getLocationKey(), forecastToSave.getEffectiveDate())){
            Forecast saveForecast = save(forecastToSave);
            log.info("{} forecast saved to the database.");
            return ResponseEntity.status(HttpStatus.CREATED).body(saveForecast);
        }else {
            log.info("No new forecast to save.");
            //No es necesario validar si está presente con Optional, porque ya verificamos en la condición que existe
            return ResponseEntity.status(HttpStatus.OK).body(getByLocationKeyAndEffectiveDate(locationKey, forecastToSave.getEffectiveDate()));
        }
    }
}
