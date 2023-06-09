package com.company.operations.services.decimal;

import com.company.operations.enums.OperationType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@DisplayName("Addition Operation Test")
public class AdditionOperationTest {

    @InjectMocks
    private AdditionOperation additionOperation;

    @BeforeEach
    public void setup() {
        additionOperation = new AdditionOperation();
    }

    @DisplayName("Execute addition operation Ok test")
    @ParameterizedTest(name = "{index} => numbers={0}, expected={1}")
    @MethodSource("additionDataProvider")
    public void executeAdditionOperationOKTest(List<String> numbers, String expected) {
        String result = additionOperation.execute(numbers);
        assertEquals(expected, result);
    }

    private static Stream<Arguments> additionDataProvider() {
        return Stream.of(
                Arguments.of(Arrays.asList("123", "11"), "134"),
                Arguments.of(Arrays.asList("123456789012345678901", "12345678"), "123456789012358024579"),
                Arguments.of(Arrays.asList("123", "11", "128", "256", "512"), "1030")
        );
    }

    @Test
    @DisplayName("Get Operation Type Test")
    public void getOperationTypeTest() {
        assertEquals(OperationType.DECIMAL_ADDITION, additionOperation.getOperationType());
    }
}
