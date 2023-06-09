package com.company.operations.services.factory;

import com.company.operations.enums.OperationType;
import com.company.operations.services.Operations;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * A Factory is needed for scalability, it allows us to add several implementations of operations
 */
@Component
public class OperationsFactory {
    private final Map<OperationType, Operations> operations = new EnumMap<>(OperationType.class);

    /**
     * @param iOperations A list of all the implementations of Operations
     */
    public OperationsFactory(List<Operations> iOperations) {
        iOperations.stream()
                .collect(Collectors.toSet())
                .forEach(r -> this.operations.put(r.getOperationType(), r));
    }

    /**
     * @param operationType An operation, eg: DECIMAL_ADDITION, DECIMAL_SUBTRACTION, DECIMAL_MULTIPLY, DECIMAL_DIVIDE
     * @return the implementation of Operations for that particular Decimal Type, e.g: DECIMAL_ADDITION
     */
    public Operations getOperationByType(OperationType operationType) {
        return operations.get(operationType);
    }
}
