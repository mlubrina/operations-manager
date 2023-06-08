package com.company.operations.entities;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NumbersValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidNumbers {
    String message() default "Invalid numbers";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
