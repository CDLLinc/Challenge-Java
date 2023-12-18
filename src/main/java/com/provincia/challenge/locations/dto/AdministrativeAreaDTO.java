package com.provincia.challenge.locations.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.provincia.challenge.locations.entity.Country;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdministrativeAreaDTO {

    @JsonAlias("ID")
    private String id;

    @JsonAlias("LocalizedName")
    private String localizedName;

    @JsonAlias("CountryID")
    private Country country;
}
