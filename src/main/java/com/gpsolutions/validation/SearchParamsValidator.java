package com.gpsolutions.validation;

import com.gpsolutions.annotations.ValidSearchParams;
import com.gpsolutions.enums.SearchParams;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Map;


public class SearchParamsValidator implements ConstraintValidator<ValidSearchParams, HttpServletRequest> {

    @Override
    public void initialize(ValidSearchParams constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(HttpServletRequest request, ConstraintValidatorContext context) {
        Map<String, String[]> params = request.getParameterMap();
        if (params.isEmpty()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Parameters can not be empty").addConstraintViolation();
            return false;
        }
        SearchParams[] validParams = SearchParams.values();
        for (String key : params.keySet()) {
            String[] values = params.get(key);
            boolean valid = false;
            if (!key.equalsIgnoreCase(SearchParams.AMENITIES.toString()) && values.length > 1) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(String.format("Several parameters for %s is not allowed here", key)).addConstraintViolation();
                return valid;
            }
            for (int i = 0; i < validParams.length; i++) {
                if (key.toUpperCase().equals(validParams[i].toString())) {
                    valid = true;
                }
            }
            if (!valid) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(String.format("There is no %s attribute", key)).addConstraintViolation();
                return valid;
            }
            if (Arrays.stream(values).anyMatch(StringUtils::isEmpty)) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(String.format("Empty parameter for %s is not allowed here", key)).addConstraintViolation();
                return false;
            }
        }
        return true;
    }

}
