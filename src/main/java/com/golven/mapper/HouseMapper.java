package com.golven.mapper;

import com.golven.mapper.provider.HouseProvider;
import com.golven.pojo.House;
import org.apache.ibatis.annotations.*;

import java.util.HashMap;
import java.util.List;

public interface HouseMapper {

    @Select("select h.*,u.*,d.*,t.* from h_house h ,h_district d,h_type t,h_users u where h.user_id=u.id and h.district_id=d.did and h.typeid=t.typeid order by hid")
    @Results(id = "houseMap",value = {
            //users表
            @Result(column = "id",property = "users.id"),
            @Result(column = "name",property = "users.name"),
            @Result(column = "psw",property = "users.psw"),
            @Result(column = "sex",property = "users.sex"),
            @Result(column = "birth",property = "users.birth"),
            @Result(column = "head_img",property = "users.head_img"),
            @Result(column = "role",property = "users.role"),
            //district表
            @Result(column = "did",property = "district.did"),
            @Result(column = "parentid",property = "district.parentId"),
            @Result(column = "dis_name",property = "district.disName"),
            //type表
            @Result(column = "typeid",property = "houseType.typeid"),
            @Result(column = "typedesc",property = "houseType.typedesc"),
    })
    List<House> selectAll();

    @Insert("insert into h_house values(null,#{user_id},#{district_id},#{typeid},#{price},#{areas},#{title},#{mark},#{equipment},#{address},#{imgs})")
    void addHouse(House house);

    @Select("select * from h_house where hid = #{hid}")
    House selectById(Integer hid);
    @Update("update h_house set user_id=#{user_id},district_id=#{district_id},typeid=#{typeid},price=#{price},areas=#{areas},title=#{title},mark=#{mark},equipment=#{equipment},address=#{address},imgs=#{imgs} where hid=#{hid}")
    void updateHouse(House house);

    @SelectProvider(type = HouseProvider.class,method = "selectByCondition")
    @ResultMap("houseMap")
    List<House> selectByCondition(HashMap<String,String> map);

}