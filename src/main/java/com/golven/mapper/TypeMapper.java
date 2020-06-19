package com.golven.mapper;

import com.golven.pojo.District;
import com.golven.pojo.HouseType;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TypeMapper {
    //selectAll
    @Select("select * from h_type")
    List<HouseType> selectAll();
    @Select("select * from h_type where typeid=#{typeid}")
    HouseType selectById(Integer typeid);
}
