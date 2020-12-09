package com.example.restaurant.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.restaurant.entity.Bill;
import com.example.restaurant.service.DeskService;
import com.example.restaurant.service.DishService;
import com.example.restaurant.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.*;

@RestController
@RequestMapping("order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @Autowired
    DishService dishService;

    @Autowired
    DeskService deskService;

    @PostMapping("bill")
    public String bill(@RequestParam Map<String, Object> map) {
        System.out.println("接到堂食订单");
        int user_id = Integer.parseInt(String.valueOf(map.get("user_id")));
        String no = generateNo(user_id, true);
        System.out.println(no);
        int desk_id = Integer.parseInt(String.valueOf(map.get("desk_id")));
        double total_price = Double.parseDouble(String.valueOf(map.get("total_price")));
        Timestamp time = new Timestamp(Long.parseLong(no.substring(2, 15)));
        System.out.println(time);

        String s = (String) map.get("map");
        Map num = JSONObject.parseObject(s, Map.class);
        for (Object key : num.keySet()) {
            int dish_id = Integer.parseInt(String.valueOf(key));
            int dishNum = Integer.parseInt(String.valueOf(num.get(String.valueOf(key))));
//            System.out.println(key + "=" + dishNum);
            if (dishNum != 0) {
                //增加对应菜品的销量
                dishService.addSales(dish_id, dishNum);
                orderService.bill(no, user_id, desk_id, dish_id, dishNum, time, total_price);
            }
        }
        //修改对应餐桌的信息
        deskService.occupy(desk_id);
        return no;
    }

    @PostMapping("checkBill")
    public String checkBill(String user_id) {
        ArrayList<Bill> result = orderService.checkBill(Integer.parseInt(user_id));
        if (result.size() == 0) return "0";
        else return result.get(0).getNo();
    }

    @PostMapping("addBill")
    public String addBill(@RequestParam Map<String, Object> map) {
        int user_id = Integer.parseInt(String.valueOf(map.get("user_id")));
        ArrayList<Bill> result = orderService.checkBill(user_id);
        String no = result.get(0).getNo();
        int desk_id = result.get(0).getDesk_id();
        Timestamp time = result.get(0).getTime();
        double addPrice = Double.parseDouble(String.valueOf(map.get("addPrice")));
        double total_price = result.get(0).getTotal_price() + addPrice;

        String s = (String) map.get("map");
        Map num = JSONObject.parseObject(s, Map.class);
        for (Object key : num.keySet()) {
            int dish_id = Integer.parseInt(String.valueOf(key));
            int dishNum = Integer.parseInt(String.valueOf(num.get(String.valueOf(key))));
            if (dishNum != 0) {
                //增加销量
                dishService.addSales(dish_id, dishNum);
                boolean flag = false;
                for (Bill bill : result) {
                    //若该菜品已经添加过
                    if (bill.getDish_id() == dish_id) {
                        flag = true;
                        break;
                    }
                }
                if (flag) {//已经存在的商品直接增加数量
                    orderService.addBill(user_id, dish_id, dishNum);
                } else {//否则插入新的行
                    orderService.bill(no, user_id, desk_id, dish_id, dishNum, time, total_price);
                }
            }
        }
        //全部设为新的total_price
        orderService.setPrice(no, total_price);

        return no;
    }

    @PostMapping("takeaway")
    public String takeaway(@RequestParam Map<String, Object> map) {
        System.out.println("接到外卖订单");
        int user_id = Integer.parseInt(String.valueOf(map.get("user_id")));
        String no = generateNo(user_id, false);
        String realname = String.valueOf(map.get("realname"));
        String phone = String.valueOf(map.get("phone"));
        String address = String.valueOf(map.get("address"));
        double total_price = Double.parseDouble(String.valueOf(map.get("total_price")));
        Timestamp time = new Timestamp(Long.parseLong(no.substring(2, 15)));
        System.out.println(time);

        String s = (String) map.get("map");
        Map num = JSONObject.parseObject(s, Map.class);
        for (Object key : num.keySet()) {
            int dish_id = Integer.parseInt(String.valueOf(key));
            int dishNum =  Integer.parseInt(String.valueOf(num.get(String.valueOf(key))));
            if (dishNum != 0) {
                dishService.addSales(dish_id, dishNum);
                orderService.takeaway(no, user_id, realname, phone, address, dish_id, dishNum, time, total_price);
            }
        }
        return no;
    }

    @PostMapping("finish")
    public int finish(String no) {
        if (no.startsWith("00")) {
            //释放对应的餐桌
            int desk_id = orderService.checkBill(Integer.parseInt(no.substring(15))).get(0).getDesk_id();
            deskService.free(desk_id);
            //完成订单
            return orderService.finishBill(no);
        } else if (no.startsWith("01")) return orderService.finishTakeaway(no);

        else return 0;
    }

    @GetMapping("allOrders")
    public ArrayList<Map<String, Object>> allOrders() {
        ArrayList<Map<String, Object>> maps = orderService.allOrders();
        return getMaps(maps);
    }

    @PostMapping("userOrders")
    public ArrayList<Map<String, Object>> userOrders(String user_id) {
        ArrayList<Map<String, Object>> maps = orderService.userOrders(Integer.parseInt(user_id));
        return getMaps(maps);
    }

    //整合查询结果，将同一个订单号的结果合在一起
    private ArrayList<Map<String, Object>> getMaps(ArrayList<Map<String, Object>> maps) {
        ArrayList<Map<String, Object>> result = new ArrayList<>();
        for (Map<String, Object> map : maps) {
            //判断该no是否已经存在
            boolean flag = false;
            for (Map<String, Object> order : result) {
                if (String.valueOf(order.get("no")).equals(String.valueOf(map.get("no")))) {
                    result.remove(order);
                    flag = true;
                    HashMap<String, Integer> dishNum = (HashMap<String, Integer>) order.get("dish");
                    dishNum.put(String.valueOf(map.get("dish_name")), (Integer) map.get("num"));
                    order.put("dish", dishNum);
                    result.add(order);
                    break;
                }
            }
            if (flag == true) continue;
            //result中没有含有该订单
            //不管堂食还是外卖都要有的值：单号、时间、完成、总价、菜名和数量
            HashMap<String, Object> order = new HashMap<>();
            order.put("no", map.get("no"));
            order.put("time", map.get("time"));
            order.put("finished", map.get("finished"));
            order.put("total_price", map.get("total_price"));
            HashMap<String, Integer> dishNum = new HashMap<>();
            dishNum.put(String.valueOf(map.get("dish_name")), (Integer) map.get("num"));

            order.put("dish", dishNum);
            if (String.valueOf(map.get("no")).startsWith("00")) {
                //堂食：username、number、size、
                order.put("takeaway", 0);
                order.put("username", map.get("username"));
                order.put("number", map.get("number"));
                order.put("size", map.get("size"));
            } else {
                //外卖：realname、phone、address
                order.put("takeaway", 1);
                order.put("realname", map.get("realname"));
                order.put("phone", map.get("phone"));
                order.put("address", map.get("address"));
            }
            result.add(order);
        }
        // 排序
        result.sort(new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                Timestamp t1 = (Timestamp) o1.get("time");
                Timestamp t2 = (Timestamp) o2.get("time");
                return t2.compareTo(t1);
            }
        });
        return result;
    }

    private String generateNo(int user_id, boolean bill) {
        StringBuilder builder = new StringBuilder();
        builder.append(bill ? "00" : "01");
        builder.append(System.currentTimeMillis());
        builder.append(user_id);
        return builder.toString();
    }

}
