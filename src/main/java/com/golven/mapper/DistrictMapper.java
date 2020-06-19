package com.golven.mapper;

import com.golven.pojo.District;
import com.golven.pojo.Users;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DistrictMapper {
    //selectAll
    @Select("select * from h_district where parentId=#{did}")
    @Results({
            @Result(column = "dis_name",property = "disName")
    })
    List<District> getDisByParentId(Integer did);
    @Select("select * from h_district where did=#{did}")
    @Results({
            @Result(column = "dis_name",property = "disName")
    })
    District getStreetBydid(Integer did);
}
