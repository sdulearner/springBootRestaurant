package com.example.restaurant.service;

import com.example.restaurant.dao.OrderMapper;
import com.example.restaurant.entity.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Map;

@Service
public class OrderService {
    @Autowired
    OrderMapper orderMapper;

    public int bill(String no, int user_id, int desk_id, int dish_id, int num, Timestamp time, double total_price) {
        return orderMapper.bill(no, user_id, desk_id, dish_id, num, time, total_price);
    }

    public ArrayList<Bill> checkBill(int user_id) {
        ArrayList<Bill> result = orderMapper.checkBill(user_id);
        if (result == null) return new ArrayList<>();
        else return result;
    }

    public int addBill(int user_id, int dish_id, int addNum) {
        return orderMapper.addBill(user_id, dish_id, addNum);
    }

    public int setPrice(String no, double newPrice) {
        return orderMapper.setPrice(no, newPrice);
    }

    public int takeaway(String no, int user_id, String realname, String phone, String address, int dish_id, int num, Timestamp time, double price) {
        return orderMapper.takeaway(no, user_id, realname, phone, address, dish_id, num, time, price);
    }

    public int finishBill(String no) {
        return orderMapper.finishBill(no);
    }

    public int finishTakeaway(String no) {
        return orderMapper.finishTakeaway(no);
    }

    public ArrayList<Map<String, Object>> allOrders() {
        ArrayList<Map<String, Object>> result = orderMapper.allBills();
        if (result == null) {
            result = orderMapper.allTakeaways();
        } else {
            result.addAll(orderMapper.allTakeaways());
        }
        if (result == null) return new ArrayList<>();
        else return result;
    }

    public ArrayList<Map<String, Object>> userOrders(int user_id) {
        ArrayList<Map<String, Object>> result = orderMapper.userBills(user_id);
        if (result == null) {
            result = orderMapper.userTakeaways(user_id);
        } else {
            result.addAll(orderMapper.userTakeaways(user_id));
        }
        if (result == null) return new ArrayList<>();
        else return result;
    }
}
