package com.company.operations.validators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintValidatorContext;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Operations Validator Test")
public class OperationsValidatorTest {

    @Mock
    private ConstraintValidatorContext context;

    @Mock
    private ConstraintValidatorContext.ConstraintViolationBuilder violationBuilder;

    private OperationsValidator operationsValidator;

    @BeforeEach
    public void setup() {
        operationsValidator = new OperationsValidator();
        lenient().when(context.buildConstraintViolationWithTemplate(anyString())).thenReturn(violationBuilder);
    }

    @Test
    @DisplayName("Test should be false with null operations")
    public void testShouldBeFalseWithNullOperations() {
        assertFalse(operationsValidator.isValid(null, context));
        verify(context, times(1)).buildConstraintViolationWithTemplate("Operation is missing");
    }

    @Test
    @DisplayName("Test should be false if Operation is Blank")
    public void testShouldBeFalseIfOperationIsBlank() {
        assertFalse(operationsValidator.isValid("", context));
        verify(context, times(1)).buildConstraintViolationWithTemplate("Operation cannot be blank");
    }

    @Test
    @DisplayName("Test should be false if it's an invalid operation")
    public void testShouldBeFalseIfAnInvalidOperation() {
        assertFalse(operationsValidator.isValid("invalid_operation", context));
    }

    @Test
    @DisplayName("Test should be true if it's a valid operation")
    public void testShouldBeTrueIfItsAValidOperation() {
        assertTrue(operationsValidator.isValid("DECIMAL_ADDITION", context));
    }
}
