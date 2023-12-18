package com.provincia.challenge.forecast.repository;

import com.provincia.challenge.forecast.entity.Forecast;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ForecastRepository extends JpaRepository<Forecast, Integer> {

    boolean existsByLocationKeyAndEffectiveDate(String locationKey, LocalDateTime effectiveDate);

    Optional<Forecast> getByLocationKeyAndEffectiveDate(String locationKey, LocalDateTime effectiveDate);

    List<Forecast> getByLocationKey(String locationKey);

}
