package com.example.restaurant.service;

import com.example.restaurant.dao.ExpenseMapper;
import com.example.restaurant.entity.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;

@Service
public class ExpenseService {
    @Autowired
    ExpenseMapper expenseMapper;


    public int addExpense(String name, double amount, Timestamp time) {
        return expenseMapper.addExpense(name, amount, time);
    }

    public ArrayList<Expense> allExpenses() {
        ArrayList<Expense> result = expenseMapper.allExpenses();
        if (result != null) return result;
        else return new ArrayList<>();
    }

    public int changeExpenseInfo(String name, double amount,Timestamp newTime ,int id) {
        return expenseMapper.changeExpenseInfo(name, amount,newTime ,id);
    }

    public int delete(int id) {
        return expenseMapper.delete(id);
    }
}
