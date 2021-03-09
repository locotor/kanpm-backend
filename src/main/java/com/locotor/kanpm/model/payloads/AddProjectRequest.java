package com.locotor.kanpm.model.payloads;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class AddProjectRequest {

    @NotBlank
    @Size(min = 4, max = 40)
    private String projectName;

    @NotBlank
    private String teamId;

    @Size(max = 255)
    private String description;

}