package com.gpsolutions.annotations;

import com.gpsolutions.validation.SearchParamsValidator;

import java.lang.annotation.*;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = {SearchParamsValidator.class})
@Target( ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidSearchParams {
    String message() default "Invalid search parameters";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
