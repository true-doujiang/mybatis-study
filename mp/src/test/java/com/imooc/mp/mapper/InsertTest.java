package com.imooc.mp.mapper;

import com.imooc.mp.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

/**
 * @Author: -小野猪-
 * @Date: 2019-06-29 0:22
 * @Version: 1.0
 * @Desc:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class InsertTest {

    @Autowired
    private UserMapper userMapper;


    @Test
    public void insert() {
        User user = new User();
        //user.setId(1L);
        user.setName("张无忌");
        user.setAge(30);
        user.setManagerId(1088248166370832385L);
        user.setCreateTime(LocalDateTime.now());
        int rows = userMapper.insert(user);
        System.out.println("影响记录数：" + rows);
    }


}
