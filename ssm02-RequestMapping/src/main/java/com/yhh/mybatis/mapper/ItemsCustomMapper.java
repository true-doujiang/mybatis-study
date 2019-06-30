package com.yhh.mybatis.mapper;

import com.yhh.mybatis.entity.ItemsCustom;
import com.yhh.mybatis.vo.ItemsQueryVo;

import java.util.List;


/**
 * @Author: -小野猪-
 * @Date: 2019/6/30 19:56
 * @Version: 1.0
 * @Desc:
 */
public interface ItemsCustomMapper {

    //商品查询列表
    List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo)throws Exception;

}