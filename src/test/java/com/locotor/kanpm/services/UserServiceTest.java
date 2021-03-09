package com.locotor.kanpm.services;

import com.locotor.kanpm.mappers.UserMapper;
import com.locotor.kanpm.model.entities.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userService.list(null);
        Assertions.assertEquals(2, userList.size());
        userList.forEach(System.out::println);
    }

}
