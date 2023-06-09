package com.company.operations.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = OperationsValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidOperations {
    String message() default "Invalid Operation";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}