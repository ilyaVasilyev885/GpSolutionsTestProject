package com.gpsolutions.validation;

import com.gpsolutions.annotations.ValidHistogramParams;
import com.gpsolutions.enums.SearchParams;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class HistogramParamsValidator implements ConstraintValidator<ValidHistogramParams, String> {

    @Override
    public void initialize(ValidHistogramParams constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String param, ConstraintValidatorContext context) {
        SearchParams[] validParams = new SearchParams[4];
        validParams[0] = SearchParams.COUNTRY;
        validParams[1] = SearchParams.CITY;
        validParams[2] = SearchParams.AMENITIES;
        validParams[3] = SearchParams.BRAND;
        boolean valid = false;
        for (int i = 0; i < validParams.length; i++) {
            if (param.equalsIgnoreCase(validParams[i].toString())) {
                valid = true;
            }
        }
        if (!valid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(String.format("There is no %s attribute", param)).addConstraintViolation();
            return valid;
        }
        return valid;
    }

}
