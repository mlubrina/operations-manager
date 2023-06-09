package com.company.operations.dto;

import com.company.operations.validators.ValidNumbers;
import com.company.operations.validators.ValidOperations;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DefaultRequestDto {
    @ValidOperations
    private String operation;

    @ValidNumbers
    private List<String> numbers;
}
