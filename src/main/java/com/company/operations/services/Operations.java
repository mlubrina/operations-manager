package com.company.operations.services;

import com.company.operations.enums.OperationType;

import java.util.List;

public interface Operations {
    String execute(List<String> elements);
    OperationType getOperationType();
}
