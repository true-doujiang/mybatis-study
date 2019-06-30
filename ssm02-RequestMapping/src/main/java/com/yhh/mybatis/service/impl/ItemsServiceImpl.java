package com.yhh.mybatis.service.impl;

import java.util.List;

import com.yhh.mybatis.entity.Items;
import com.yhh.mybatis.entity.ItemsCustom;
import com.yhh.mybatis.mapper.ItemsCustomMapper;
import com.yhh.mybatis.mapper.ItemsMapper;
import com.yhh.mybatis.service.ItemsService;
import com.yhh.mybatis.vo.ItemsQueryVo;
import org.springframework.beans.BeanUtils;
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

    @Autowired
    private ItemsMapper itemsMapper;


    @Override
    public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception {
        //通过ItemsMapperCustom查询数据库
        return itemsMapperCustom.findItemsList(itemsQueryVo);
    }

    @Override
    public ItemsCustom findItemsById(Integer id) throws Exception {

        Items items = itemsMapper.selectByPrimaryKey(id);
        //中间对商品信息进行业务处理
        //....
        //返回ItemsCustom
        ItemsCustom itemsCustom = new ItemsCustom();
        //将items的属性值拷贝到itemsCustom  //spring提供的工具
        BeanUtils.copyProperties(items, itemsCustom);
        return itemsCustom;
    }

    @Override
    public void updateItems(Integer id, ItemsCustom itemsCustom) throws Exception {

        //添加业务校验，通常在service接口对关键参数进行校验
        //校验 id是否为空，如果为空抛出异常   id如果为int类型不好判断为空，所以改用Integer

        /*
         * 更新商品信息使用updateByPrimaryKeyWithBLOBs根据id更新items表中所有字段，
         * 包括 大文本类型字段
         * updateByPrimaryKeyWithBLOBs要求必须转入id
         */
        //这一步是为了确保一定有id
        itemsCustom.setId(id);
        itemsMapper.updateByPrimaryKeyWithBLOBs(itemsCustom);
    }


}