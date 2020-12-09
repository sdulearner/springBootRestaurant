package com.example.restaurant.service;

import com.example.restaurant.dao.DataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataService {
    @Autowired
    DataMapper dataMapper;

    public int billCount() {
        return dataMapper.billCount();
    }

    public int takeawayCount() {
        return dataMapper.takeawayCount();
    }
}
