package com.provincia.challenge.forecast.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HeadlineDTO {

    @JsonAlias({"EffectiveDate"})
    private String effectiveDate;

    @JsonAlias({"EndDate"})
    private String endDate;

    @JsonAlias({"Text"})
    private String text;

    @JsonAlias({"Severity"})
    private int severity;
}
