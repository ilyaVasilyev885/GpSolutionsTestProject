package com.gpsolutions.mappers;

import com.gpsolutions.dto.AddressDTO;
import com.gpsolutions.entities.AddressModel;
import org.mapstruct.Mapper;

@Mapper
public interface AddressMapper {
    AddressDTO toDto(AddressModel model);
    AddressModel toModel(AddressDTO dto);
}
