package com.yhh.mybatis.vo;

import com.yhh.mybatis.entity.Orders;
import lombok.Data;
import lombok.ToString;

/**
 * @Author: -小野猪-
 * @Date: 2019/6/30 12:19
 * @Version: 1.0
 * @Desc:
 */
@Data
@ToString
public class OrdersCustom extends Orders {

	/*为了测试一对一查询使用resultType方式用的
	 添加用户属性
	  USER.username,
	  USER.sex,
	  USER.address */

    private String username;
    private String sex;
    private String address;

}
