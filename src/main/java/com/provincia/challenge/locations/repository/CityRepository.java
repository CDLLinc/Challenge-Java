package com.provincia.challenge.locations.repository;

import com.provincia.challenge.locations.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, String> {

}
