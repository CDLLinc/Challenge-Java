package com.provincia.challenge.locations.repository;

import com.provincia.challenge.locations.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, String> {
}
