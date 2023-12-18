package com.provincia.challenge.forecast.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DayDTO {

    @JsonAlias("IconPhrase")
    private String iconPhrase;
}
