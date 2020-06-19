package com.golven.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Users {
    private Integer id;
    private String name;
    private String psw;
    private String sex;
    private Date birth;
    private String head_img;
    private String role;
}