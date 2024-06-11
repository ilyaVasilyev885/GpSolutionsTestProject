package com.gpsolutions.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import static com.gpsolutions.constants.GpSolutionsConstants.NOT_NULL_MESSAGE_PROPERTY;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressDTO {

    @NotNull(message = "houseNumber " + NOT_NULL_MESSAGE_PROPERTY)
    private Integer houseNumber;

    @NotNull(message = "street " + NOT_NULL_MESSAGE_PROPERTY)
    private String street;

    @NotNull(message = "city " + NOT_NULL_MESSAGE_PROPERTY)
    private String city;

    @NotNull(message = "country " + NOT_NULL_MESSAGE_PROPERTY)
    private String country;

    @NotNull(message = "postalCode " + NOT_NULL_MESSAGE_PROPERTY)
    private String postalCode;

    @Override
    public String toString() {
        return houseNumber + " " + street + ", " + city + ", " + postalCode + ", " + country;
    }
}
