package com.provincia.challenge.locations.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "regions")
public class Region {

    @Id
    private String id;
    @NotBlank
    private String localizedName;
    @NotBlank
    private String englishName;

}
