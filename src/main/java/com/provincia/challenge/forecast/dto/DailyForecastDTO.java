package com.provincia.challenge.forecast.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DailyForecastDTO {

    @JsonAlias("Temperature")
    private TemperatureDTO temperature;

    @JsonAlias({"Day"})
    private DayDTO day;

    @JsonAlias({"Night"})
    private NightDTO night;
}
