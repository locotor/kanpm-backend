package com.locotor.kanpm.model.payloads;

import lombok.Data;

@Data
public class ApiResponse {
    private Boolean success;
    private String message;
    private Object data;

    public ApiResponse(Boolean success) {
        this.success = success;
    }
    
    public ApiResponse(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}