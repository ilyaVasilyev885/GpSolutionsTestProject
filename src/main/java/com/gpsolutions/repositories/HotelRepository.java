package com.gpsolutions.repositories;

import com.gpsolutions.entities.HotelModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface HotelRepository extends JpaRepository<HotelModel, Long>, JpaSpecificationExecutor<HotelModel> {

}
