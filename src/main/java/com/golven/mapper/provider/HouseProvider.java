package com.golven.mapper.provider;

import com.golven.pojo.House;
import com.mysql.jdbc.StringUtils;
import org.apache.ibatis.jdbc.SQL;

import java.util.HashMap;
import java.util.List;

public class HouseProvider {
    public String selectByCondition(HashMap<String,String> condition){
        SQL sql = new SQL(){{
            SELECT("*");
            FROM("h_house h,h_users u,h_district d,h_type t");
            //where可以写多个
            WHERE(" h.user_id=u.id  AND h.district_id=d.did AND h.typeid=t.typeid ");
            //条件拼接,判断是否为null，区域
            if(!StringUtils.isNullOrEmpty(condition.get("district_id"))){
                //可以使用#{}引用map中的值
                WHERE("d.parentId=#{district_id}");
            }
            //动态拼接条件--房型
            if(!StringUtils.isNullOrEmpty(condition.get("typeid"))){
                WHERE(" h.typeid=#{typeid} ");
            }
            //条件拼接租金
            if(!StringUtils.isNullOrEmpty(condition.get("min_price"))&&!StringUtils.isNullOrEmpty(condition.get("max_price"))){
                //可以使用#{}引用map中的值
                WHERE("h.price >= #{min_price} and h.price <=#{max_price}");
            }
            if(!StringUtils.isNullOrEmpty(condition.get("min_area"))&&!StringUtils.isNullOrEmpty(condition.get("max_area"))){
                //可以使用#{}引用map中的值
                WHERE("h.areas >= #{min_area} and h.areas <= #{max_area}");
            }
            ORDER_BY("h.hid");
        }};
        return sql.toString();
    }
}
