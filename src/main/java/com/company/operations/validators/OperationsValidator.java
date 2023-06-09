package com.company.operations.validators;

import com.company.operations.enums.OperationType;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OperationsValidator implements ConstraintValidator<ValidOperations, String> {
    @Override
    public void initialize(ValidOperations constraintAnnotation) {
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext context) {

        if (s == null) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Operation is missing")
                    .addConstraintViolation();
            return false;
        }

        if (s.isBlank()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Operation cannot be blank")
                    .addConstraintViolation();
            return false;
        }

        try {
            OperationType.valueOf(s.toUpperCase());
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }
}
