package com.example.restaurant.service;

import com.example.restaurant.dao.MaterialMapper;
import com.example.restaurant.entity.Material;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;

@Service
public class MaterialService {
    @Autowired
    MaterialMapper materialMapper;

    public int addMaterial(String name, double remainder) {
        return materialMapper.addMaterial(name, remainder);
    }

    public ArrayList<Material> allMaterials() {
        ArrayList<Material> result = materialMapper.allMaterials();
        if (result != null) return result;
        else return new ArrayList<>();
    }

    public int changeMaterialInfo(String name, double remainder, int id) {
        return materialMapper.changeMaterialInfo(name, remainder, id);
    }

    public int delete(int id) {
        return materialMapper.delete(id);
    }
}
