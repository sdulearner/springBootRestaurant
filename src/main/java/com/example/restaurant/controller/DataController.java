package com.example.restaurant.controller;


import com.example.restaurant.entity.Dish;
import com.example.restaurant.service.DataService;
import com.example.restaurant.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/data")
public class DataController {
    @Autowired
    DataService dataService;

    @Autowired
    DishService dishService;

    @GetMapping("billData")
    public ArrayList<Map<String, Object>> billData() {
        int billCount = dataService.billCount();
        int takeawayCount = dataService.takeawayCount();
        ArrayList<Map<String, Object>> result = new ArrayList<>();
        for (int i = 0; i < 2; i++) {

            Map<String, Object> item = new HashMap<>();
            if (i == 0) {
                item.put("name", "堂食");
                item.put("value", billCount);
            } else {
                item.put("name", "外卖");
                item.put("value", takeawayCount);
            }
            result.add(item);
        }
        return result;
    }

    @GetMapping("dishData")
    public ArrayList<Map<String, Object>> dishData() {
        ArrayList<Dish> allDishes = dishService.allDishes();
        ArrayList<Map<String, Object>> result = new ArrayList<>();
        for (Dish dish : allDishes) {
            Map<String, Object> item = new HashMap<>();
            item.put("name", dish.getDish_name());
            item.put("value", dish.getSales());
            result.add(item);
        }
        return result;
    }


}
