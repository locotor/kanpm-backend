package com.locotor.kanpm.model.payloads;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class SignInRequest {
    @NotBlank
    private String usernameOrEmail;

    @NotBlank
    private String password;
}