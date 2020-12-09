package com.example.restaurant.dao;

import com.example.restaurant.entity.Desk;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.ArrayList;

@Mapper
public interface DeskMapper {
    @Select("select * from desk where desk_enable=1")
    ArrayList<Desk> allDesks();

    @Update("update desk set free=1 where desk_id=#{id}")
    Integer free(int id);

    @Update("update desk set free=0 where desk_id=#{id}")
    Integer occupy(int id);

    @Select("select free from desk where desk_id=#{id}")
    Integer isFree(int id);

    @Update("update desk set number=#{number},size=#{size} where desk_id=#{id}")
    Integer changeDeskInfo(String number,int size,int id);

    @Insert("insert into desk (number,size) values (#{number},#{size})")
    Integer addDesk(String number,int size);

    @Update("update desk set desk_enable=0 where desk_id=#{id}")
    Integer delete(int id);

}
