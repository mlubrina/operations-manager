package com.company.operations.entities;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class NumbersValidator implements ConstraintValidator<ValidNumbers, List<String>> {
    @Override
    public void initialize(ValidNumbers constraintAnnotation) {
    }

    @Override
    public boolean isValid(List<String> numbers, ConstraintValidatorContext context) {
        for (String number : numbers) {
            if (!number.matches("[0-9]+")) {
                return false;
            }
        }
        return true;
    }
}
