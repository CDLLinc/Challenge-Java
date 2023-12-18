package com.provincia.challenge.forecast.service;

import com.provincia.challenge.forecast.dto.ForecastDTO;
import com.provincia.challenge.forecast.entity.Forecast;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ForecastService {

    ResponseEntity<?> dayOfExternalApi(String locationKey);
    List<Forecast> list();
    boolean existsByLocationKeyAndEffectiveDate(String key, LocalDateTime effectiveDate);
    boolean existsById(int id);
    void deleteById(int id);
    Optional<Forecast> getOne(int id);
    List<Forecast> getForecast();
    Optional<Forecast> getByLocationKeyAndEffectiveDate(String locationKey, LocalDateTime effectiveDate);
    List<Forecast> getByLocationKey(String locationKey);
    Forecast save(Forecast forecast);
    ResponseEntity<?> saveForecastIfNeeded(String locationKey, ForecastDTO forecastDTO);
}
