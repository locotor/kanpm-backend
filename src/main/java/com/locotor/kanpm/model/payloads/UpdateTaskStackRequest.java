package com.locotor.kanpm.model.payloads;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UpdateTaskStackRequest {

    @NotBlank
    private String id;

    @NotBlank
    private String projectId;

    @Size(min = 1, max = 64)
    private String stackName;

    private Integer alignment;

    private Integer sortBy;

}
