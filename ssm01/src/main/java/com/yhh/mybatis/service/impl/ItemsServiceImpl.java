package com.yhh.mybatis.service.impl;

import java.util.List;

import com.yhh.mybatis.entity.ItemsCustom;
import com.yhh.mybatis.mapper.ItemsCustomMapper;
import com.yhh.mybatis.service.ItemsService;
import com.yhh.mybatis.vo.ItemsQueryVo;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @Author: -小野猪-
 * @Date: 2019/6/30 19:54
 * @Version: 1.0
 * @Desc: 商品管理
 */
public class ItemsServiceImpl implements ItemsService {
	
	//<!-- mapper扫描器 --> 已经将mapper实现类在spring容器里实例化了
	
	@Autowired //先按ByType，找不到就按ByName
	private ItemsCustomMapper itemsMapperCustom;


	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception {
		//通过ItemsMapperCustom查询数据库
		return itemsMapperCustom.findItemsList(itemsQueryVo);
	}



}