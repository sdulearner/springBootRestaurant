package com.example.restaurant.dao;

import com.example.restaurant.entity.Material;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.ArrayList;

@Mapper
public interface MaterialMapper {
    @Insert("insert into material (m_name,remainder) values (#{name},#{remainder})")
    Integer addMaterial(String name, double remainder);

    @Select("select * from material where m_enable=1")
    ArrayList<Material> allMaterials();

    @Update("update material set m_name=#{name},remainder=#{remainder} where m_id=#{id}")
    Integer changeMaterialInfo(String name, double remainder, int id);

    @Update("update material set m_enable=0 where m_id=#{id}")
    Integer delete(int id);
}
