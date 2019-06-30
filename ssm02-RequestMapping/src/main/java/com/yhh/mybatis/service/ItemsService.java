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
    public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception;

    //根据id查询商品信息
    //为了拓展性放回ItemsCustom   建议用Integer  id如果为int类型不好判断为空，所以改用Integer
    public ItemsCustom findItemsById(Integer id) throws Exception;

    //修改商品信息   这里第一个参数还是id 是为了强调id必填
    //建议用Integer    id如果为int类型不好判断为空，所以改用Integer
    public void updateItems(Integer id,ItemsCustom itemsCustom) throws Exception;


}