package com.provincia.challenge.forecast.entity;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "forecast_daily")
@ApiModel(value = "Entidad Modelo del Clima", description = "Información completa que se almacena de un registro del clima")
public class Forecast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank
    private String locationKey;
    @NotBlank
    private LocalDateTime effectiveDate;
    @NotBlank
    private LocalDateTime endDate;
    @NotBlank
    private String text;
    @NotNull
    private int Severity;
    @NotNull
    private int minTemperature;
    @NotNull
    private int maxTemperature;
    @NotBlank
    private String unitTemperature;
    @NotBlank
    private String dayIcon;
    @NotBlank
    private String nightIcon;

}
