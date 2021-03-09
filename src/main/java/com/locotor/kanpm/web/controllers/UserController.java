package com.locotor.kanpm.web.controllers;

import com.locotor.kanpm.model.entities.User;
import com.locotor.kanpm.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("getUser/{id}")
    public User getUser(@PathVariable String id) {
        return userService.getById(id);
    }
}