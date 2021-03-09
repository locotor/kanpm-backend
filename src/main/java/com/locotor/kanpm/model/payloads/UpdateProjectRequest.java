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

    @Size(min = 4, max = 40)
    private String projectName;

    @Size(max = 255)
    private String description;

    private String ownerId;

}