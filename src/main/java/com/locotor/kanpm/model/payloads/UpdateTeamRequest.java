package com.locotor.kanpm.model.payloads;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UpdateTeamRequest {
    
    @NotBlank
    private String id;

    @Size(min = 4, max = 40)
    private String teamName;

    @Size(max = 255)
    private String description;

    private String ownerId;

}