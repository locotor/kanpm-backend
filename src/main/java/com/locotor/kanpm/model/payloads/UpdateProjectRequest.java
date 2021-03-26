package com.locotor.kanpm.model.payloads;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UpdateProjectRequest {
    
    @NotBlank
    private String id;

    @NotBlank
    private String teamId;

    @Size(min = 1, max = 64)
    private String projectName;

    @Size(max = 256)
    private String description;

    private String ownerId;

}