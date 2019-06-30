package com.yhh.mybatis.service;

import com.yhh.mybatis.entity.ItemsCustom;
import com.yhh.mybatis.vo.ItemsQueryVo;
import java.util.List;

/**
 * @Author: -小野猪-
 * @Date: 2019/6/30 19:56
 * @Version: 1.0
 * @Desc: 商品管理service
 */
public interface ItemsService {
	
	//商品查询列表
	List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception;
	


}