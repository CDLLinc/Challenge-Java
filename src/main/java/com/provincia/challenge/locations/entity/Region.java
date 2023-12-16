package com.provincia.challenge.locations.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@Table(name = "regions")
public final class Region {

    @Id
    private final String id;
    @NotBlank
    private final String localizedName;
    @NotBlank
    private final String englishName;

    public Region(String id, String localizedName, String englishName) {
        this.id = id;
        this.localizedName = localizedName;
        this.englishName = englishName;
    }

}
