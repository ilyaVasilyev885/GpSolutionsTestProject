package com.gpsolutions.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HotelDTO {

    private Long id;

    @NotNull(message = "name" + " {validation.constraints.NotNull.message}")
    private String name;

    @NotNull(message = "brand" + " {validation.constraints.NotNull.message}")
    private String brand;

    private String description;

    @NotNull(message = "address" + " {validation.constraints.NotNull.message}")
    @Valid
    private AddressDTO address;

    @NotNull(message = "contacts" + " {validation.constraints.NotNull.message}")
    @Valid
    private ContactDTO contacts;

    @NotNull(message = "arrivalTime" + " {validation.constraints.NotNull.message}")
    @Valid
    private ArrivalTimeDTO arrivalTime;

    private List<String> amenities;
}
