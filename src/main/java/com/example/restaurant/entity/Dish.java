package com.example.restaurant.entity;

public class Dish {
    private int dish_id;
    private String dish_name;
    private double price;
    private int sales;
    private int dish_enable;
    private int index;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getDish_id() {
        return dish_id;
    }

    public void setDish_id(int dish_id) {
        this.dish_id = dish_id;
    }

    public String getDish_name() {
        return dish_name;
    }

    public void setDish_name(String dish_name) {
        this.dish_name = dish_name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    public int getDish_enable() {
        return dish_enable;
    }

    public void setDish_enable(int dish_enable) {
        this.dish_enable = dish_enable;
    }
}
