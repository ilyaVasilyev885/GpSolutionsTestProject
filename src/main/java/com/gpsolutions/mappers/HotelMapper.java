package com.gpsolutions.mappers;

import com.gpsolutions.dto.HotelDTO;
import com.gpsolutions.entities.AmenityModel;
import com.gpsolutions.entities.HotelModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Mapper(uses = {AddressMapper.class, AmenityMapper.class, ContactMapper.class})
public interface HotelMapper {
    @Mapping(source = "checkIn", target = "arrivalTime.checkIn")
    @Mapping(source = "checkOut", target = "arrivalTime.checkOut")
    @Mapping(source = "model", target = "amenities", qualifiedByName = "amenities")
    HotelDTO toDto(HotelModel model);

    @Mapping(source = "arrivalTime.checkIn", target = "checkIn")
    @Mapping(source = "arrivalTime.checkOut", target = "checkOut")
    HotelModel toModel(HotelDTO dto);

    @Named("amenities")
    default List<String> getListFromModels(HotelModel source) {
        return Optional.ofNullable(source.getAmenities()).orElse(Collections.emptyList())
                .stream()
                .map(AmenityModel::getName)
                .toList();
    }
}
