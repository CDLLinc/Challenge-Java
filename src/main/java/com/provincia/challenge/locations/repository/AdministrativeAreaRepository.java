package com.provincia.challenge.locations.repository;

import com.provincia.challenge.locations.entity.AdministrativeArea;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministrativeAreaRepository extends JpaRepository<AdministrativeArea, String> {
}
