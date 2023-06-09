package com.company.operations.controllers;

import com.company.operations.dto.DefaultRequestDto;
import com.company.operations.enums.OperationType;
import com.company.operations.services.factory.OperationsFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/operations")
@Validated
public class OperationsController {

    private final OperationsFactory operations;

    public OperationsController(OperationsFactory operationsFactory) {
        this.operations = operationsFactory;
    }

    @PostMapping("/execute")
    public ResponseEntity<String> executeOperation(@Valid @RequestBody DefaultRequestDto requestBody) {
        return ResponseEntity.ok(
                operations.getOperationByType(OperationType.valueOf(requestBody.getOperation().toUpperCase()))
                        .execute(requestBody.getNumbers()));
    }
}
