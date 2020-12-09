package com.example.restaurant.controller;

import com.example.restaurant.entity.User;
import com.example.restaurant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("login")
    public int login(String username, String password, String admi) {
        return userService.login(username, password, Integer.parseInt(admi));
    }

    @PostMapping("userInfo")
    public ArrayList<User> userInfo(String id) {
        return userService.userInfo(id);
    }

    @PostMapping("changeUserInfo")
    public int changeUserInfo(String realname, String phone, String address, String id) {
        return userService.changeUserInfo(realname, phone, address, Integer.parseInt(id));
    }
}
