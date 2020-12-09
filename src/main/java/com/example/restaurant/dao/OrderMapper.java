package com.example.restaurant.dao;

import com.example.restaurant.entity.Bill;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Map;

@Mapper
public interface OrderMapper {
    @Insert("insert into bill (no,user_id,desk_id,dish_id,num,time,total_price) values(#{no},#{user_id},#{desk_id},#{dish_id},#{num},#{time},#{total_price})")
    Integer bill(String no, int user_id, int desk_id, int dish_id, int num, Timestamp time, double total_price);

    @Select("select * from bill where user_id=#{user_id} and finished=0")
    ArrayList<Bill> checkBill(int user_id);

    @Update("update bill set num=num+#{addNum} where user_id=#{user_id} and dish_id=#{dish_id} and finished=0")
    Integer addBill(int user_id, int dish_id, int addNum);

    @Update("update bill set total_price=#{newPrice} where no=#{no}")
    Integer setPrice(String no, double newPrice);

    @Insert("insert into takeaway (no,user_id,realname,phone,address,dish_id,num,time,total_price) values(#{no},#{user_id},#{realname},#{phone},#{address},#{dish_id},#{num},#{time},#{total_price})")
    Integer takeaway(String no, int user_id, String realname, String phone, String address, int dish_id, int num, Timestamp time, double total_price);

    @Update("update bill set finished=1 where no=#{no}")
    Integer finishBill(String no);

    @Update("update takeaway set finished=1 where no=#{no}")
    Integer finishTakeaway(String no);

    @Select("select * from bill natural join user natural join desk natural join dish")
    ArrayList<Map<String, Object>> allBills();

    @Select("select * from takeaway natural join dish")
    ArrayList<Map<String, Object>> allTakeaways();

    @Select("select * from bill natural join user natural join desk natural join dish where user_id=#{user_id}")
    ArrayList<Map<String, Object>> userBills(int user_id);

    @Select("select * from takeaway natural join dish where user_id=#{user_id}")
    ArrayList<Map<String, Object>> userTakeaways(int user_id);
}
