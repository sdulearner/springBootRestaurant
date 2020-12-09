package com.example.restaurant.service;

import com.example.restaurant.dao.UserMapper;
import com.example.restaurant.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public int login(String username, String password, int admi) {

        Integer result = userMapper.login(username, password, admi);
        if (result != null)
            return result;
        else return 0;
    }

    public ArrayList<User> userInfo(String id) {
        return userMapper.userInfo(Integer.parseInt(id));
    }

    public int changeUserInfo(String realname, String phone, String address, int id) {
        return userMapper.changeUserInfo(realname, phone, address, id);
    }
}
