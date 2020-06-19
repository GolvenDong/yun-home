package com.golven.pojo;

import lombok.Data;

@Data
public class House {
    private Integer hid;
    private Users users;//关联user_id
    private District district;//关联district_id
    private HouseType houseType;//关联type_id
    private Double price;
    private Double areas;
    private String title;
    private String mark;
    private String equipment;
    private String address;
    private String imgs;

    //新增，补上原始字段
    private Integer user_id;
    private Integer district_id;
    private Integer typeid;
}