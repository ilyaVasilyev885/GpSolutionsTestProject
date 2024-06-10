package com.gpsolutions.repositories;

import com.gpsolutions.entities.AmenityModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AmenityRepository extends JpaRepository<AmenityModel, Long> {
    AmenityModel findByNameIgnoreCase(String name);
}
