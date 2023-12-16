package com.provincia.challenge.locations.clients;

import com.provincia.challenge.locations.dto.RegionDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "accuwheater-region", url = "${accuwheather.url}/locations/v1")
public interface AccuwheaterClientRest {

    @GetMapping(value = "/regions", consumes = MediaType.APPLICATION_JSON_VALUE)
    List<RegionDTO> getRegions(@RequestParam("apikey") String apiKey, @RequestParam("language") String language);
}
