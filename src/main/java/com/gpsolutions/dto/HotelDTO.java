package com.gpsolutions.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

import static com.gpsolutions.constants.GpSolutionsConstants.NOT_NULL_MESSAGE_PROPERTY;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HotelDTO {

    private Long id;

    @NotNull(message = "name " + NOT_NULL_MESSAGE_PROPERTY)
    private String name;

    @NotNull(message = "brand " + NOT_NULL_MESSAGE_PROPERTY)
    private String brand;

    private String description;

    @NotNull(message = "address " + NOT_NULL_MESSAGE_PROPERTY)
    @Valid
    private AddressDTO address;

    @NotNull(message = "contacts " + NOT_NULL_MESSAGE_PROPERTY)
    @Valid
    private ContactDTO contacts;

    @NotNull(message = "arrivalTime " + NOT_NULL_MESSAGE_PROPERTY)
    @Valid
    private ArrivalTimeDTO arrivalTime;

    private List<String> amenities;
}
