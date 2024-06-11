package com.gpsolutions.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HotelBasicResponseDTO {
    private Long id;
    private String name;
    private String description;
    private String address;
    private String phone;
}
