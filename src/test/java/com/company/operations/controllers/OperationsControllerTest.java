package com.company.operations.controllers;

import com.company.operations.dto.DefaultRequestDto;
import com.company.operations.enums.OperationType;
import com.company.operations.services.Operations;
import com.company.operations.services.factory.OperationsFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Operations Controller Test")
public class OperationsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OperationsFactory operationsFactory;

    @MockBean
    private Operations operation;

    @BeforeEach
    public void setup() {
        when(operationsFactory.getOperationByType(any(OperationType.class))).thenReturn(operation);
        when(operation.execute(any())).thenReturn("100");
    }

    @Test
    @DisplayName("Test should be OK when post: /operation/execute with valid json is called ")
    public void testShouldBeOkWhenPostOperationExecuteControllerIsCalled() throws Exception {
        DefaultRequestDto request = DefaultRequestDto.builder()
                .operation(OperationType.DECIMAL_ADDITION.name())
                .numbers(Arrays.asList("50", "50"))
                .build();

        mockMvc.perform(post("/operations/execute")
                        .content("{\"operation\":\"DECIMAL_ADDITION\",\"numbers\":[\"50\",\"50\"]}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("100"));
    }
}
