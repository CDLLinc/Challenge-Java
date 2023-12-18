package com.provincia.challenge.forecast.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "DTO para representar el clima")
public class ForecastDTO {

    @JsonAlias("Headline")
    private HeadlineDTO headline;

    @JsonAlias("DailyForecasts")
    private List<DailyForecastDTO> dailyForecasts;
}
