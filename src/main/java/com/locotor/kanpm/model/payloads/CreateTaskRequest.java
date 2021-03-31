package com.locotor.kanpm.model.payloads;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class CreateTaskRequest {
    
    @NotBlank
    @Size(min = 1, max = 128)
    private String description;

    @NotBlank
    private String stackId;

    private String principalUserId;

}
