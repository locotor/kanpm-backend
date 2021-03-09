package com.locotor.kanpm.model.payloads;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class SignUpRequest {

    @NotBlank
    @Size(min = 3, max = 64)
    private String userName;

    @NotBlank
    @Size(min = 6, max = 64)
    private String password;

    @NotBlank
    private String captcha;

}