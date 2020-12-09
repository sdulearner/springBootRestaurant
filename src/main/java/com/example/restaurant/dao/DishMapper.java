package com.example.restaurant.dao;

import com.example.restaurant.entity.Dish;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.ArrayList;

@Mapper
public interface DishMapper {
    @Select("select *from dish where dish_enable=1")
    ArrayList<Dish> allDishes();

    @Update("update dish set sales=sales+#{addNum} where dish_id=#{id}")
    Integer addSales(int id, int addNum);

    @Update("update dish set dish_name=#{dish_name},price=#{price} where dish_id=#{id}")
    Integer changeDishInfo(String dish_name, double price, int id);

    @Insert("insert into dish (dish_name,price) values(#{dish_name},#{price})")
    Integer addDish(String dish_name, double price);

    @Update("update dish set dish_enable=0 where dish_id=#{id}")
    Integer delete(int id);
}
