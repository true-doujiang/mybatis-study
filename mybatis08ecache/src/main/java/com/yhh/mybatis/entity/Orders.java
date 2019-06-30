package com.yhh.mybatis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * @Author: -小野猪-
 * @Date: 2019/6/30 12:18
 * @Version: 1.0
 * @Desc:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Orders {

    private Integer id;
    private Integer userId;
    private String number;
    private Date createtime;
    private String note;

    //为了测试一对一查询使用resultMap方式用的
    //用户信息  (查询订单关联查询用户信息，使用resultMap 才添加上的)
    private User user;

    //订单明细   为了测试一对多查询用的 ( findOrdersAndOrderDetailResultMap())
    private List<Orderdetail> orderdetails;

}
