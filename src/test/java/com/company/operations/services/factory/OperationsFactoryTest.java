package com.company.operations.services.factory;

import com.company.operations.enums.OperationType;
import com.company.operations.services.Operations;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Operation Factory Test")
class OperationsFactoryTest {

    @Mock
    private Operations mockOperation1;

    private OperationsFactory operationsFactory;

    @BeforeEach
    public void setup() {
        when(mockOperation1.getOperationType()).thenReturn(OperationType.DECIMAL_ADDITION);

        List<Operations> operations = Arrays.asList(mockOperation1);
        operationsFactory = new OperationsFactory(operations);
    }

    @Test
    @DisplayName("Test should Get Operation By Type")
    void testShouldGetOperationByType() {
        assertSame(mockOperation1, operationsFactory.getOperationByType(OperationType.DECIMAL_ADDITION));
    }
}
