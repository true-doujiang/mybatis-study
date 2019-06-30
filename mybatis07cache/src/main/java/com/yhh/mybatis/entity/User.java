package com.yhh.mybatis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author: -小野猪-
 * @Date: 2019/6/30 8:38
 * @Version: 1.0
 * @Desc: implements Serializable是为了使用二级缓存
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User implements Serializable {

    //属性名和数据库表的字段对应
    private int id;
    private String username;
    private String sex;
    private Date birthday;
    private String address;



    //用户创建的订单列表   多对多查询用的
    private List<Orders> ordersList;


}
