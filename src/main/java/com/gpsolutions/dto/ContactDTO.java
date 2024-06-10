package com.gpsolutions.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContactDTO {

    @NotNull(message = "phone" + " {validation.constraints.NotNull.message}")
    private String phone;
    @NotNull(message = "email" + " {validation.constraints.NotNull.message}")
    @Email(message = "{validation.constraints.Email.message}")
    private String email;
}
