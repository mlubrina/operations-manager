package com.company.operations.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class NumbersValidator implements ConstraintValidator<ValidNumbers, List<String>> {
    @Override
    public void initialize(ValidNumbers constraintAnnotation) {
    }

    @Override
    public boolean isValid(List<String> numbers, ConstraintValidatorContext context) {

        if (numbers == null) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Numbers are missing")
                    .addConstraintViolation();
            return false;
        }

        if (numbers.isEmpty()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Numbers list cannot be empty")
                    .addConstraintViolation();
            return false;
        }

        for (String number : numbers) {
            if (!number.matches("[0-9]+")) {
                return false;
            }
        }
        return true;
    }
}
