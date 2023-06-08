package com.company.operations.controllers;

import com.company.operations.entities.MathRequestDto;
import com.company.operations.enums.OperationType;
import com.company.operations.services.factory.OperationsFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/math")
@Validated
public class MathOperationsController {

    private final OperationsFactory operations;

    public MathOperationsController(OperationsFactory operationsFactory) {
        this.operations = operationsFactory;
    }

    @PostMapping("/execute")
    public ResponseEntity<String> executeMathOperation(@Valid @RequestBody MathRequestDto mathRequest, BindingResult bindingResult) {
        return ResponseEntity.ok(
                    operations.getOperationByType(OperationType.valueOf(mathRequest.getOperation().toUpperCase()))
                            .execute(mathRequest.getNumbers()));
    }
}
