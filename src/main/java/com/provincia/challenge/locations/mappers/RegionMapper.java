package com.provincia.challenge.locations.mappers;

import com.provincia.challenge.locations.dto.RegionDTO;
import com.provincia.challenge.locations.entity.Region;

public class RegionMapper {

    public static Region mapRegionDTOToRegionEntity(RegionDTO regionDTO) {
        Region region = new Region();
        region.setId(regionDTO.getId());
        region.setLocalizedName(regionDTO.getLocalizedName());
        region.setEnglishName(regionDTO.getEnglishName());
        return region;
    }
}
