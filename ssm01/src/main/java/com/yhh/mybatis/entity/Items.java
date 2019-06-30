package com.yhh.mybatis.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Author: -小野猪-
 * @Date: 2019/6/30 19:53
 * @Version: 1.0
 * @Desc:
 */
@Data
public class Items {

    private Integer id;
    private String name;
    private Float price;
    private String pic;
    private Date createtime;
    private String detail;
}
