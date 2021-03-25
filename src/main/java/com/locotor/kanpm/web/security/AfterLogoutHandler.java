package com.locotor.kanpm.web.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.locotor.kanpm.model.enums.ResponseCode;
import com.locotor.kanpm.model.payloads.ResponseData;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AfterLogoutHandler implements LogoutSuccessHandler {


    @Override
    public void onLogoutSuccess(HttpServletRequest request,
                                HttpServletResponse response,
                                Authentication authentication) throws IOException, ServletException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"未登录或登录失效");
        ResponseData responseData = ResponseData.build(ResponseCode.USER_NOT_LOGIN);
        response.setContentType("application/json;charset=utf-8");
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(), responseData);
    }
}