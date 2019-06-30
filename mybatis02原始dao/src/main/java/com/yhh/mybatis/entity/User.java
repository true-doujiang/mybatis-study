package com.yhh.mybatis.entity;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @Author: -小野猪-
 * @Date: 2019/6/29 23:56
 * @Version: 1.0
 * @Desc:
 */
@Data
@ToString
public class User {

    //属性名和数据库表的字段对应
    private int id;
    private String username;// 用户姓名
    private String sex;// 性别
    private Date birthday;// 生日
    private String address;// 地址

}
