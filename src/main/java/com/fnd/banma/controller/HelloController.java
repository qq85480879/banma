package com.fnd.banma.controller;

import com.fnd.banma.pojo.User;
import com.fnd.banma.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {

    @Autowired
    private UserService userService;

    @RequestMapping("/user")
    public List<User> getUser(){

        List<User> userList = userService.query();

        System.out.println(userList);

        return userService.query();
    }

}
