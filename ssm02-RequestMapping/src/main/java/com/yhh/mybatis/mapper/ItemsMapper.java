package com.yhh.mybatis.mapper;

import com.yhh.mybatis.entity.Items;
import com.yhh.mybatis.entity.ItemsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: -小野猪-
 * @Date: 2019/6/30 23:50
 * @Version: 1.0
 * @Desc:
 */
public interface ItemsMapper {

    int countByExample(ItemsExample example);

    int deleteByExample(ItemsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Items record);

    int insertSelective(Items record);

    List<Items> selectByExampleWithBLOBs(ItemsExample example);

    List<Items> selectByExample(ItemsExample example);

    Items selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Items record, @Param("example") ItemsExample example);

    int updateByExampleWithBLOBs(@Param("record") Items record, @Param("example") ItemsExample example);

    int updateByExample(@Param("record") Items record, @Param("example") ItemsExample example);

    int updateByPrimaryKeySelective(Items record);

    int updateByPrimaryKeyWithBLOBs(Items record);

    int updateByPrimaryKey(Items record);

}
