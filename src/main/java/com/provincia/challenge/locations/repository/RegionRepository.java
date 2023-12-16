package com.provincia.challenge.locations.repository;

import com.provincia.challenge.locations.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region, String> {
}
