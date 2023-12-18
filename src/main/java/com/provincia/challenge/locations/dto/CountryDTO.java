package com.provincia.challenge.locations.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "DTO para representar un país")
public final class CountryDTO {

    @JsonAlias("ID")
    private String id;

    @JsonAlias("LocalizedName")
    private String localizedName;

}
