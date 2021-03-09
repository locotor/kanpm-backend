package com.locotor.kanpm.web.controllers;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.locotor.kanpm.model.entities.User;
import com.locotor.kanpm.model.payloads.ApiResponse;
import com.locotor.kanpm.model.payloads.SignUpRequest;
import com.locotor.kanpm.services.UserService;

import com.locotor.kanpm.web.common.Captcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthenticationController {

    final AuthenticationManager authenticationManager;

    final UserService userService;

    final PasswordEncoder passwordEncoder;

    private final String captchaKey = "captcha";

    @Autowired
    AuthenticationController(AuthenticationManager authenticationManager, UserService userService,
                             PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> signUp(@RequestBody SignUpRequest request, HttpSession session) {
        String requestCaptcha = request.getCaptcha();
        String sessionCaptcha = (String) session.getAttribute(captchaKey);
        if (StringUtils.isEmpty(requestCaptcha)) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, "Captcha can not be null!"));
        }
        if (!sessionCaptcha.equalsIgnoreCase(requestCaptcha)) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, "Captcha is wrong!"));
        }
        session.removeAttribute(captchaKey);
        User userTest = (User) userService.loadUserByUsername(request.getUserName());
        if (userTest != null) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, "Username is already taken!"));
        }

        User user = new User(request.getUserName(), passwordEncoder.encode(request.getPassword()));
        boolean insertResult = userService.save(user);
        if (insertResult) {
            return ResponseEntity.ok(new ApiResponse(true, "sign up successfully"));
        } else {
            return ResponseEntity.badRequest().body(new ApiResponse(false, "sign up failed"));
        }

    }

    @GetMapping("/verifyUserNameOrEmail")
    public ResponseEntity<Object> verifyUserNameOrEmail(String userNameOrEmail) {
        if (userNameOrEmail.isBlank()) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, "parameter should not be blank"));
        }
        User userTest = (User) userService.loadUserByUsername(userNameOrEmail);
        if (userTest != null) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, "this username is already exist"));
        }
        return ResponseEntity.ok(true);
    }

    @GetMapping("/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Captcha captcha = new Captcha();
        BufferedImage img = captcha.getImage();
        String text = captcha.getText();
        request.getSession().setAttribute(captchaKey, text);
        Captcha.output(img, response.getOutputStream());
    }

}