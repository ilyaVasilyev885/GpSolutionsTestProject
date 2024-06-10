package com.gpsolutions.facades.impl;

import com.gpsolutions.dto.HotelBasicResponseDTO;
import com.gpsolutions.dto.HotelDTO;
import com.gpsolutions.entities.AmenityModel;
import com.gpsolutions.entities.HotelModel;
import com.gpsolutions.facades.HotelFacade;
import com.gpsolutions.mappers.AmenityMapper;
import com.gpsolutions.mappers.HotelBasicResponseMapper;
import com.gpsolutions.mappers.HotelMapper;
import com.gpsolutions.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class HotelFacadeImpl implements HotelFacade {
    private HotelMapper hotelMapper;
    private HotelService hotelService;
    private HotelBasicResponseMapper hotelBasicResponseMapper;
    private AmenityMapper amenityMapper;

    @Autowired
    public HotelFacadeImpl(HotelMapper hotelMapper, HotelService hotelService, HotelBasicResponseMapper hotelBasicResponseMapper, AmenityMapper amenityMapper) {
        this.hotelMapper = hotelMapper;
        this.hotelService = hotelService;
        this.hotelBasicResponseMapper = hotelBasicResponseMapper;
        this.amenityMapper = amenityMapper;
    }


    @Override
    public HotelDTO findById(Long id) {
        HotelModel hotel = hotelService.findById(id);
        return hotelMapper.toDto(hotel);
    }

    @Override
    public List<HotelBasicResponseDTO> findAll() {
        return hotelService.findAll()
                .stream()
                .map(hotelMapper::toDto)
                .map(hotelBasicResponseMapper::toResponseDTO)
                .toList();
    }

    @Override
    public HotelBasicResponseDTO save(HotelDTO hotelDTO) {
        HotelModel saved = hotelService.save(hotelMapper.toModel(hotelDTO));
        return hotelBasicResponseMapper.toResponseDTO(hotelMapper.toDto(saved));
    }

    @Override
    public void addAmenities(Long id, List<String> amenities) {
        List<AmenityModel> models = amenities.stream()
                .map(amenityMapper::toModelFromString)
                .toList();
        hotelService.addAmenities(id, models);
    }

    @Override
    public List<HotelBasicResponseDTO> findAll(Map<String, String[]> params) {
        return hotelService.findByParams(params)
                .stream()
                .map(hotelMapper::toDto)
                .map(hotelBasicResponseMapper::toResponseDTO)
                .toList();
    }
}
