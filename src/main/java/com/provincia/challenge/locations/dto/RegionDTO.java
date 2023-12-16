package com.provincia.challenge.locations.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

@Data
public final class RegionDTO {

    @JsonAlias("ID")
    private final String id;

    @JsonAlias("LocalizedName")
    private final String localizedName;

    @JsonAlias("EnglishName")
    private final String englishName;

    public RegionDTO(String id, String localizedName, String englishName) {
        this.id = id;
        this.localizedName = localizedName;
        this.englishName = englishName;
    }
}
