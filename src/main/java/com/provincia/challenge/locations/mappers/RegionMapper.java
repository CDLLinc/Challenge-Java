package com.provincia.challenge.locations.mappers;

import com.provincia.challenge.locations.dto.RegionDTO;
import com.provincia.challenge.locations.entity.Region;

public class RegionMapper {

    public static Region mapRegionDTOToRegionEntity(RegionDTO regionDTO) {
        return new Region(
                regionDTO.getId(),
                regionDTO.getLocalizedName(),
                regionDTO.getEnglishName()
                );
    }
}
