package com.example.restaurant.service;

import com.example.restaurant.dao.DeskMapper;
import com.example.restaurant.entity.Desk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class DeskService {
    @Autowired
    DeskMapper deskMapper;

    public ArrayList<Desk> allDesks() {
        ArrayList<Desk> result = deskMapper.allDesks();
        if (result != null) return result;
        else return new ArrayList<>();
    }

    public int free(int id) {
        return deskMapper.free(id);
    }

    public int occupy(int id) {
        return deskMapper.occupy(id);
    }

    public int isFree(int id) {
        return deskMapper.isFree(id);
    }

    public int changeDeskInfo(String number, int size, int id) {
        return deskMapper.changeDeskInfo(number, size, id);
    }

    public int addDesk(String number, int size) {
        return deskMapper.addDesk(number, size);
    }

    public int delete(int id) {
        return deskMapper.delete(id);
    }
}
