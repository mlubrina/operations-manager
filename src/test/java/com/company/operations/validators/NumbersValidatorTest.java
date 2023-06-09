package com.company.operations.validators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Numbers Validator Test")
class NumbersValidatorTest {

    private NumbersValidator numbersValidator;

    @Mock
    ConstraintValidatorContext context;

    @Mock
    ConstraintValidatorContext.ConstraintViolationBuilder violationBuilder;

    @Captor
    ArgumentCaptor<String> captor;

    @BeforeEach
    public void setup() {
        numbersValidator = new NumbersValidator();
        lenient().when(context.buildConstraintViolationWithTemplate(anyString())).thenReturn(violationBuilder);
    }

    @Test
    @DisplayName("Test Is Valid Given Null List Should Return False")
    public void testIsValidGivenNullListShouldReturnFalse() {
        assertFalse(numbersValidator.isValid(null, context));
        verify(context).buildConstraintViolationWithTemplate(captor.capture());
        assertEquals("Numbers are missing", captor.getValue());
    }

    @Test
    @DisplayName("Test Is Valid Given Empty List Should Return False")
    public void testIsValidGivenEmptyListShouldReturnFalse() {
        assertFalse(numbersValidator.isValid(Collections.emptyList(), context));
        verify(context).buildConstraintViolationWithTemplate(captor.capture());
        assertEquals("Numbers list cannot be empty", captor.getValue());
    }

    @Test
    @DisplayName("Test Is Valid Given List Without Non Numeric String Should Return False")
    public void testIsValidGivenListWithoutNonNumericStringShouldReturnFalse() {
        List<String> invalidNumberList = Arrays.asList("123", "abc");
        assertFalse(numbersValidator.isValid(invalidNumberList, context));
    }

    @Test
    @DisplayName("Test Is Valid Given Valid List Should Return True")
    public void testIsValidGivenValidListShouldReturnTrue() {
        List<String> validNumberList = Arrays.asList("123", "456");
        assertTrue(numbersValidator.isValid(validNumberList, context));
    }
}
