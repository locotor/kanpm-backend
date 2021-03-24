package com.locotor.kanpm.web.common;

import com.locotor.kanpm.model.enums.ResponseCode;
import com.locotor.kanpm.model.payloads.ResponseData;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
public class CommonResponseAdvice implements ResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
            Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (((ResponseData) body).getCode() == ResponseCode.SUCCESS.getCode()) {
            return ResponseEntity.ok(body);
        } else {
            return ResponseEntity.badRequest().body(body);
        }
    }

}
