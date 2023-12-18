package com.provincia.challenge.locations.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public final class CountryDTO {

    @JsonAlias("ID")
    private String id;

    @JsonAlias("LocalizedName")
    private String localizedName;

}
