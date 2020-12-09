package com.example.restaurant.entity;

public class Desk {
    private int desk_id;
    private String number;
    private int size;
    private int free;
    private int desk_enable;

    public int getDesk_id() {
        return desk_id;
    }

    public void setDesk_id(int desk_id) {
        this.desk_id = desk_id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getFree() {
        return free;
    }

    public void setFree(int free) {
        this.free = free;
    }

    public int getDesk_enable() {
        return desk_enable;
    }

    public void setDesk_enable(int desk_enable) {
        this.desk_enable = desk_enable;
    }
}
