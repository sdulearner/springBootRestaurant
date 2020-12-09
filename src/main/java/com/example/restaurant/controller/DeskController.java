package com.example.restaurant.controller;

import com.example.restaurant.entity.Desk;
import com.example.restaurant.service.DeskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/desk")
public class DeskController {
    @Autowired
    DeskService deskService;

    @GetMapping("allDesks")
    public ArrayList<Desk> allDesks() {
        return deskService.allDesks();
    }

    @PostMapping("free")
    public int free(String id) {
        return deskService.free(Integer.parseInt(id));
    }

    @PostMapping("occupy")
    public int occupy(String id) {
        return deskService.occupy(Integer.parseInt(id));
    }

    @PostMapping("isFree")
    public int isFree(String id) {
        return deskService.isFree(Integer.parseInt(id));
    }

    @PostMapping("changeDeskInfo")
    public int changeDeskInfo(String number, String size, String id) {
        return deskService.changeDeskInfo(number, Integer.parseInt(size), Integer.parseInt(id));
    }

    @PostMapping("addDesk")
    public int addDesk(String number, String size) {
        return deskService.addDesk(number, Integer.parseInt(size));
    }

    @PostMapping("delete")
    public int delete(String id) {
        return deskService.delete(Integer.parseInt(id));
    }
}
