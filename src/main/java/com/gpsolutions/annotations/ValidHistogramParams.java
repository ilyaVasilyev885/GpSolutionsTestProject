package com.gpsolutions.annotations;

import com.gpsolutions.validation.HistogramParamsValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {HistogramParamsValidator.class})
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidHistogramParams {
    String message() default "Invalid histogram attribute";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
