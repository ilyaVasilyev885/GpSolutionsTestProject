package com.gpsolutions.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressDTO {

    @NotNull(message = "houseNumber" + " {validation.constraints.NotNull.message}")
    private Integer houseNumber;

    @NotNull(message = "street" + " {validation.constraints.NotNull.message}")
    private String street;

    @NotNull(message = "city" + " {validation.constraints.NotNull.message}")
    private String city;

    @NotNull(message = "country" + " {validation.constraints.NotNull.message}")
    private String country;

    @NotNull(message = "postalCode" + " {validation.constraints.NotNull.message}")
    private String postalCode;

    @Override
    public String toString() {
        return houseNumber + " " + street + ", " + city + ", " + postalCode + ", " + country;
    }
}
