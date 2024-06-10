package com.gpsolutions.mappers;

import com.gpsolutions.dto.ContactDTO;
import com.gpsolutions.entities.ContactModel;
import org.mapstruct.Mapper;

@Mapper
public interface ContactMapper {
    ContactDTO toDto(ContactModel model);
    ContactModel toModel(ContactDTO dto);
}
