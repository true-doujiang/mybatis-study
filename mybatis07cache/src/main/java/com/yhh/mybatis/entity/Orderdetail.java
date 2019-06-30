package com.yhh.mybatis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
public class Orderdetail {

    private Integer id;
    private Integer ordersId;
    private Integer itemsId;
    private Integer itemsNum;

    //明细对应的商品信息
    private Items items;
}
