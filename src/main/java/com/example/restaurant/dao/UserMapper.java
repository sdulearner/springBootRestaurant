package com.example.restaurant.dao;

import com.example.restaurant.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.ArrayList;

@Mapper
public interface UserMapper {
    @Select("select * from user where username=#{username} and password=#{password} and admi=#{admi}")
    Integer login(String username, String password, int admi);

    @Select("select * from user where user_id=#{id}")
    ArrayList<User> userInfo(int id);

    @Update("update user set realname=#{realname},phone=#{phone},address=#{address} where user_id=#{id}")
    Integer changeUserInfo(String realname, String phone, String address,int id);
}
