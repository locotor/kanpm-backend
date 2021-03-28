package com.locotor.kanpm.model.payloads;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class CreateTaskStackRequest {
    
    @Size(min = 1, max = 128)
    private String stackName;

    @NotBlank
    private String projectId;

    private String previousId;

}
