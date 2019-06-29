package com.imooc.mp.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.imooc.mp.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: -小野猪-
 * @Date: 2019-06-29 16:58
 * @Version: 1.0
 * @Desc:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DeleteTest {


    @Autowired
    private UserMapper userMapper;



    @Test
    public void deleteById() {
        int rows = userMapper.deleteById(1134013564424658946L);
        System.out.println("删除条数：" + rows);
    }

    @Test
    public void deleteByMap() {
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("name", "张无忌1");
        columnMap.put("age", 31);

        int rows = userMapper.deleteByMap(columnMap);
        System.out.println("删除条数：" + rows);
    }

    @Test
    public void deleteBatchByIds() {
        int rows = userMapper.deleteBatchIds(Arrays.asList(1134345992649379841L, 1134346040569274369L));
        System.out.println("删除条数：" + rows);
    }

    @Test
    public void deleteByWrapper() {
        LambdaQueryWrapper<User> lambdaQuery = Wrappers.lambdaQuery();
        lambdaQuery.eq(User::getAge, 27).or().gt(User::getAge, 41);
        int rows = userMapper.delete(lambdaQuery);
        System.out.println("删除条数：" + rows);
    }


}
