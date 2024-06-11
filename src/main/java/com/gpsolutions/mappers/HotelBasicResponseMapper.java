package com.gpsolutions.mappers;

import com.gpsolutions.dto.HotelBasicResponseDTO;
import com.gpsolutions.dto.HotelDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface HotelBasicResponseMapper {

    @Mapping(source = "hotelDto.contacts.phone", target = "phone")
    @Mapping(expression = "java(hotelDto.getAddress().toString())", target = "address")
    HotelBasicResponseDTO toResponseDTO(HotelDTO hotelDto);
}
