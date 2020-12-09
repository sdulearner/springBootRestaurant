package com.example.restaurant.controller;

import com.example.restaurant.entity.Expense;
import com.example.restaurant.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

@RestController
@RequestMapping("/expense")
public class ExpenseController {
    @Autowired
    ExpenseService expenseService;

    @PostMapping("addExpense")
    public int addExpense(String name, String amount) {
        Timestamp time = new Timestamp(System.currentTimeMillis());
        return expenseService.addExpense(name, Double.parseDouble(amount), time);
    }

    @GetMapping("allExpenses")
    public ArrayList<Expense> allExpenses() {
        return expenseService.allExpenses();
    }

    @PostMapping("changeExpenseInfo")
    public int changeExpenseInfo(String name, String amount, String time, String id) {
        System.out.println(time);
        Timestamp newTime = Timestamp.valueOf(time);
        return expenseService.changeExpenseInfo(name, Double.parseDouble(amount), newTime, Integer.parseInt(id));
//        return 0;
    }

    @PostMapping("delete")
    public int delete(String id) {
        return expenseService.delete(Integer.parseInt(id));
    }
}
