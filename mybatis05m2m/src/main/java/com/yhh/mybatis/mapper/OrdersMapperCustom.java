package com.yhh.mybatis.mapper;

import com.yhh.mybatis.entity.Orders;
import com.yhh.mybatis.entity.User;
import com.yhh.mybatis.vo.OrdersCustom;

import java.util.List;

/**
 * @Author: -小野猪-
 * @Date: 2019/6/30 12:17
 * @Version: 1.0
 * @Desc:
 */
public interface OrdersMapperCustom {

    /**
     * 关联查询
     */
    //查询订单 关联 查询用户信息  使用resultType
    List<OrdersCustom> findOrdersUser() throws Exception;


    //查询订单关联查询用户          使用resultMap
    List<Orders> findOrdersUserResultMap() throws Exception;


    /**
     * 一对多
     */
    //查询订单(关联用户)及订单明细
    List<Orders> findOrdersAndOrderDetailResultMap() throws Exception;

    /**
     * 多对多
     */
    //查询用户购买商品信息
    List<User> findUserAndItemsResultMap() throws Exception;

}
