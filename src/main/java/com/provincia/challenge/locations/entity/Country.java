package com.provincia.challenge.locations.entity;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "countries")
@ApiModel(value = "Entidad Modelo de un País", description = "Información completa que se almacena de un país")
public final class Country {

    @Id
    private String id;
    @NotBlank
    private String localizedName;

    public Country(@NonNull String id) {
        this.id = id;
    }
}
