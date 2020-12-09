package com.example.restaurant.entity;

import java.sql.Timestamp;

public class Expense {
    private int e_id;
    private String e_name;
    private double amount;
    private Timestamp time;
    private int e_enable;

    public int getE_id() {
        return e_id;
    }

    public void setE_id(int e_id) {
        this.e_id = e_id;
    }

    public String getE_name() {
        return e_name;
    }

    public int getE_enable() {
        return e_enable;
    }

    public void setE_enable(int e_enable) {
        this.e_enable = e_enable;
    }

    public void setE_name(String e_name) {
        this.e_name = e_name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
