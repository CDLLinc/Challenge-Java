package com.provincia.challenge.locations.entity;

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
public class City {

    @Id
    private String id;
    @NotBlank
    private String localizedName;
    @ManyToOne()
    private AdministrativeArea administrativeArea;

}
