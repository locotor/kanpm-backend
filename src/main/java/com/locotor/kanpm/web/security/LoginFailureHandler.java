package com.locotor.kanpm.web.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.locotor.kanpm.model.enums.ResponseCode;
import com.locotor.kanpm.model.payloads.ResponseData;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        ResponseData respBean = setResponseData(exception);
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(), respBean);
    }

    private ResponseData setResponseData(AuthenticationException exception) {
        if (exception instanceof LockedException) {
            return ResponseData.build(ResponseCode.USER_IS_LOCK);
        } else if (exception instanceof CredentialsExpiredException) {
            return ResponseData.build(ResponseCode.CREDENTIAL_NOT_RIGHT);
        } else if (exception instanceof AccountExpiredException) {
            return ResponseData.build(ResponseCode.USER_NOT_RIGHT);
        } else if (exception instanceof DisabledException) {
            return ResponseData.build(ResponseCode.USER_DISABLE);
        } else if (exception instanceof BadCredentialsException) {
            ResponseData build = ResponseData.build(ResponseCode.AUTH_ERROR);
            return build;
        }
        return ResponseData.build(ResponseCode.USER_NOT_LOGIN);
    }

}
