package com.locotor.kanpm.model.payloads;

import com.locotor.kanpm.model.entities.User;

import lombok.Data;

@Data
public class JwtAuthenticationResponse {

    private String accessToken;

    private User user;

    private String tokenType = "Bearer";

    public JwtAuthenticationResponse(String token, User user) {
        this.accessToken = token;
        this.user = user;
    }

}