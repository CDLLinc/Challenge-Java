package com.provincia.challenge.forecast.client;

import com.provincia.challenge.forecast.dto.ForecastDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "accuwheater-forecast", url = "${url}/forecasts/v1")
public interface AccuweatherForecastClientRest {

    @GetMapping(value = "/daily/1day/{locationKey}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ForecastDTO getForecast(@PathVariable("locationKey") String locationKey, @RequestParam("apikey") String apiKey, @RequestParam("language") String language);
}
