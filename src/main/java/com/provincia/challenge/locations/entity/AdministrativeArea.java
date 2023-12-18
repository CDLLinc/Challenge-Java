package com.provincia.challenge.locations.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "administrative_areas")
public class AdministrativeArea {

    @Id
    private String id;
    @NotBlank
    private String localizedName;
    @ManyToOne()
    private Country country;

    public AdministrativeArea(@NonNull String id) {
        this.id = id;
    }
}
