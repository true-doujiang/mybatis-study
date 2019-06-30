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
     * 延迟加载
     */
    //查询订单关联查询用户，用户信息是延迟加载
    List<Orders> findOrdersUserLazyLoading() throws Exception;

}
