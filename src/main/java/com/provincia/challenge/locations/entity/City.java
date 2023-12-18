package com.provincia.challenge.locations.entity;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cities")
@ApiModel(value = "Entidad Modelo de una Ciudad", description = "Información completa que se almacena de una ciudad")
public class City {

    @Id
    private String id;
    @NotBlank
    private String localizedName;
    @ManyToOne()
    private AdministrativeArea administrativeArea;

}
