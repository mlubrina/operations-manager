package com.company.operations.exceptions;

import com.company.operations.exceptions.handlers.GlobalExceptionHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Global Exception Handler Test")
public class GlobalExceptionHandlerTest {

    @Mock
    private ConstraintViolationException constraintViolationException;

    @Mock
    private ConstraintViolation constraintViolation;

    @Mock
    private BindingResult bindingResult;

    @Mock
    private FieldError fieldError;

    private GlobalExceptionHandler handler;

    @BeforeEach
    public void setup() {
        handler = new GlobalExceptionHandler();
    }

    @Test
    @DisplayName("Test should be ok when Handle Constraint Violation Exception")
    public void testShouldBeOkWhenHandleConstraintViolationException() {
        when(constraintViolation.getMessage()).thenReturn("Error message");
        when(constraintViolationException.getConstraintViolations())
                .thenReturn(Collections.singleton(constraintViolation));

        ResponseEntity<List<String>> response = handler.handleConstraintViolationException(constraintViolationException);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(Collections.singletonList("Error message"), response.getBody());
    }

    @Test
    @DisplayName("Test should be ok when Handle Method Argument Not Valid")
    public void testShouldBeOkWhenHandleMethodArgumentNotValid() {
        String defaultMessage = "Error message";
        when(fieldError.getDefaultMessage()).thenReturn(defaultMessage);
        when(bindingResult.getFieldError()).thenReturn(fieldError);

        MethodParameter methodParameter = mock(MethodParameter.class);
        MethodArgumentNotValidException exception = new MethodArgumentNotValidException(methodParameter, bindingResult);

        ResponseEntity<String> response = handler.handleMethodArgumentNotValid(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(defaultMessage, response.getBody());
    }
}
