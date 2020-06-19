package com.golven.mapper;

import com.golven.pojo.Users;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UsersMapper{
    //selectAll
    @Select("select * from h_users")
    List<Users> selectAll();
    @Select("select * from h_users where role = '房东'")
    List<Users> selectLandord();
    @Select("select * from h_users where id = #{id}")
    Users selectById(Integer id);
    @Select("select * from h_users where name like #{abContent}")
    List<Users> selectAbstract(String abContent);
}
