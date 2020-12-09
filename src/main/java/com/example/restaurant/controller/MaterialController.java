package com.example.restaurant.controller;

import com.example.restaurant.entity.Material;
import com.example.restaurant.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.ArrayList;

@RestController
@RequestMapping("material")
public class MaterialController {
    @Autowired
    MaterialService materialService;

    @PostMapping("addMaterial")
    public int addMaterial(String name, String remainder) {
        return materialService.addMaterial(name, Double.parseDouble(remainder));
    }

    @GetMapping("allMaterials")
    public ArrayList<Material> allMaterials() {
        return materialService.allMaterials();
    }

    @PostMapping("changeMaterialInfo")
    public int changeMaterialInfo(String name, String remainder, String id) {
        return materialService.changeMaterialInfo(name, Double.parseDouble(remainder), Integer.parseInt(id));
    }

    @PostMapping("delete")
    public int delete(String id) {
        return materialService.delete(Integer.parseInt(id));
    }
}
