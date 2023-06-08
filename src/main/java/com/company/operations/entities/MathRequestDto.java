package com.company.operations.entities;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
public class MathRequestDto {
    @NotBlank(message = "Operation cannot be blank")
    @NotNull(message = "Operation is missing")
    private String operation;

    @NotEmpty(message = "Numbers list cannot be empty")
    @NotNull(message = "Numbers are missing")
    @ValidNumbers
    private List<String> numbers;
}
