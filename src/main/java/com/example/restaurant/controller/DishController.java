package com.example.restaurant.controller;

import com.example.restaurant.entity.Dish;
import com.example.restaurant.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/dish")
public class DishController {
    @Autowired
    DishService dishService;

    @GetMapping("allDishes")
    public ArrayList<Dish> allDishes() {
        return dishService.allDishes();
    }

//    @PostMapping("addSales")
//    public int addSales(String id) {
//        return dishService.addSales(Integer.parseInt(id));
//    }

    @PostMapping("changeDishInfo")
    public int changeDishInfo(String dish_name, String price, String id) {
        return dishService.changeDishInfo(dish_name, Double.parseDouble(price), Integer.parseInt(id));
    }

    @PostMapping("addDish")
    public int addDish(String dish_name, double price) {
        return dishService.addDish(dish_name, price);
    }

    @PostMapping("delete")
    public int delete(String id) {
        return dishService.delete(Integer.parseInt(id));
    }
}
