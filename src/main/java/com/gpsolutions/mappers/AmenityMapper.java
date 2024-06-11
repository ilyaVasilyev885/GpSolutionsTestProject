package com.gpsolutions.mappers;

import com.gpsolutions.dto.AmenityDTO;
import com.gpsolutions.entities.AmenityModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AmenityMapper {
    AmenityDTO toDto(AmenityModel model);

    AmenityModel toModelFromDto(AmenityDTO dto);

    @Mapping(source = "amenity", target = "name")
    AmenityModel toModelFromString(String amenity);
}
