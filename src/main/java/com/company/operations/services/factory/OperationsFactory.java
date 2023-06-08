package com.company.operations.services.factory;

import com.company.operations.enums.OperationType;
import com.company.operations.services.Operations;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class OperationsFactory {
    private final Map<OperationType, Operations> operations = new EnumMap<>(OperationType.class);

    public OperationsFactory(List<Operations> iOperations) {
        iOperations.stream()
                .collect(Collectors.toSet())
                .forEach(r -> this.operations.put(r.getOperationType(), r));
    }

    public Operations getOperationByType(OperationType operationType) {
        return operations.get(operationType);
    }
}
