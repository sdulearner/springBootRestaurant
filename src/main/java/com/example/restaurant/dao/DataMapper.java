package com.example.restaurant.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DataMapper {
    @Select("select count(distinct no) from bill")
    Integer billCount();

    @Select("select count(distinct no) from takeaway")
    Integer takeawayCount();


}
