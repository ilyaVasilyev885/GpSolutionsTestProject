package com.gpsolutions.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import static com.gpsolutions.constants.GpSolutionsConstants.EMAIL_MESSAGE_PROPERTY;
import static com.gpsolutions.constants.GpSolutionsConstants.NOT_NULL_MESSAGE_PROPERTY;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContactDTO {

    @NotNull(message = "phone " + NOT_NULL_MESSAGE_PROPERTY)
    private String phone;
    @NotNull(message = "email " + NOT_NULL_MESSAGE_PROPERTY)
    @Email(message = EMAIL_MESSAGE_PROPERTY)
    private String email;
}
