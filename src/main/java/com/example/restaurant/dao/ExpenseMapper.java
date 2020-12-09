package com.example.restaurant.dao;

import com.example.restaurant.entity.Expense;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.sql.Timestamp;
import java.util.ArrayList;

@Mapper
public interface ExpenseMapper {
    @Insert("insert into expense (e_name,amount,time) values (#{name},#{amount},#{time})")
    Integer addExpense(String name, double amount, Timestamp time);

    @Select("select * from expense where e_enable=1")
    ArrayList<Expense> allExpenses();

    @Update("update expense set e_name=#{name},amount=#{amount},time=#{newTime} where e_id=#{id}")
    Integer changeExpenseInfo(String name, double amount, Timestamp newTime, int id);

    @Update("update expense set e_enable=0 where e_id=#{id}")
    Integer delete(int id);
}
