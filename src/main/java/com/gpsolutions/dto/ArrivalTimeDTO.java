package com.gpsolutions.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class ArrivalTimeDTO {

    @NotNull(message = "checkIn" + " {validation.constraints.NotNull.message}")
    @JsonFormat(pattern="HH:mm")
    LocalTime checkIn;
    @JsonFormat(pattern="HH:mm")
    LocalTime checkOut;
}
