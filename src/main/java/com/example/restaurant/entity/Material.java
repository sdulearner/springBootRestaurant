package com.example.restaurant.entity;

public class Material {
    private int m_id;
    private String m_name;
    private double remainder;
    private int m_enable;

    public int getM_id() {
        return m_id;
    }

    public void setM_id(int m_id) {
        this.m_id = m_id;
    }

    public String getM_name() {
        return m_name;
    }

    public void setM_name(String m_name) {
        this.m_name = m_name;
    }

    public double getRemainder() {
        return remainder;
    }

    public void setRemainder(double remainder) {
        this.remainder = remainder;
    }

    public int getM_enable() {
        return m_enable;
    }

    public void setM_enable(int m_enable) {
        this.m_enable = m_enable;
    }
}
