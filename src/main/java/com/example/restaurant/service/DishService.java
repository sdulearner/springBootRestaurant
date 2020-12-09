package com.example.restaurant.service;

import com.example.restaurant.dao.DishMapper;
import com.example.restaurant.entity.Dish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class DishService {
    @Autowired
    DishMapper dishMapper;

    public ArrayList<Dish> allDishes() {
        ArrayList<Dish> result = dishMapper.allDishes();
        if (result != null) return result;
        else return new ArrayList<>();
    }

    public int addSales(int id, int addNum) {
        return dishMapper.addSales(id, addNum);
    }

    public int changeDishInfo(String dish_name, double price, int id) {
        return dishMapper.changeDishInfo(dish_name, price, id);
    }

    public int addDish(String dish_name, double price) {
        return dishMapper.addDish(dish_name, price);
    }

    public int delete(int id) {
        return dishMapper.delete(id);
    }

}
