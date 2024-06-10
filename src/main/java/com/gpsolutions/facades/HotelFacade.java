package com.gpsolutions.facades;

import com.gpsolutions.dto.HotelBasicResponseDTO;
import com.gpsolutions.dto.HotelDTO;

import java.util.List;
import java.util.Map;

public interface HotelFacade {
    HotelDTO findById(Long id);
    List<HotelBasicResponseDTO> findAll();
    HotelBasicResponseDTO save(HotelDTO hotelDTO);
    void addAmenities(Long id, List<String> amenities);
    List<HotelBasicResponseDTO> findAll(Map<String, String[]> params);
}
