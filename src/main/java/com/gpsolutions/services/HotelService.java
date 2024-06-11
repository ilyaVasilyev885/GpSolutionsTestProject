package com.gpsolutions.services;

import com.gpsolutions.entities.AmenityModel;
import com.gpsolutions.entities.HotelModel;

import java.util.List;
import java.util.Map;

public interface HotelService {
    HotelModel findById(Long id);
    List<HotelModel> findAll();
    HotelModel save(HotelModel hotelModel);
    void addAmenities(Long id, List<AmenityModel> amenities);
    List<HotelModel> findByParams(Map<String, String[]> params);
}
