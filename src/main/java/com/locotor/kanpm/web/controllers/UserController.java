package com.locotor.kanpm.web.controllers;

import com.locotor.kanpm.model.entities.User;
import com.locotor.kanpm.model.enums.ResponseCode;
import com.locotor.kanpm.model.payloads.ResponseData;
import com.locotor.kanpm.services.UserService;
import com.locotor.kanpm.web.security.CurrentUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("current-user")
    public ResponseData getCurrentUser(@CurrentUser User user) {
        if (user == null) {
            return ResponseData.build(ResponseCode.SUCCESS, user);
        } else {
            return ResponseData.build(ResponseCode.USER_NOT_LOGIN);
        }
    }

    @GetMapping("{id}")
    public User getUserById(@PathVariable String id) {
        return userService.getById(id);
    }
}