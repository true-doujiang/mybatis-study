package com.yhh.mybatis.vo;

import com.yhh.mybatis.entity.Items;
import com.yhh.mybatis.entity.ItemsCustom;
import lombok.Data;

/**
 * @Author: -小野猪-
 * @Date: 2019/6/30 19:56
 * @Version: 1.0
 * @Desc:
 */
@Data
public class ItemsQueryVo {

    //商品信息
    private Items items;
    //为了系统 可扩展性，对原始生成的po进行扩展
    private ItemsCustom itemsCustom;

}
