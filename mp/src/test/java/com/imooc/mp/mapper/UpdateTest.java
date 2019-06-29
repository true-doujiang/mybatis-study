package com.imooc.mp.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.additional.update.impl.LambdaUpdateChainWrapper;
import com.imooc.mp.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: -小野猪-
 * @Date: 2019-06-29 16:44
 * @Version: 1.0
 * @Desc:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UpdateTest {


    @Autowired
    private UserMapper userMapper;


    @Test
    public void updateById() {
        User user = new User();
        user.setId(1L);
        user.setEmail("zwj@baomidou.com");
        int rows = userMapper.updateById(user);
        System.out.println("影响记录数：" + rows);
    }

    @Test
    public void updateByWrapper1() {
        UpdateWrapper<User> userWrapper = Wrappers.update();
        userWrapper.eq("name", "李艺伟").eq("age", 28);

        User user = new User();
        user.setEmail("lyw2019@baomidou.com");
        user.setAge(29);

        int rows = userMapper.update(user, userWrapper);
        System.out.println("影响记录数：" + rows);
    }

    /**
     * 构造器参数和实体对象参数会重复出现
     */
    @Test
    public void updateByWrapper2() {
        User userWhere = new User();
        userWhere.setName("李艺伟");

        UpdateWrapper<User> userWrapper = Wrappers.update(userWhere);
        userWrapper.eq("name", "李艺伟").eq("age", 28);

        User user = new User();
        user.setAge(29);

        int rows = userMapper.update(user, userWrapper);
        System.out.println("影响记录数：" + rows);
    }

    @Test
    public void updateByWrapper3() {
        UpdateWrapper<User> userWrapper = Wrappers.update();
        // set 要修改的字段
        userWrapper.eq("name", "李艺伟").eq("age", 29).set("age", 30);

        int rows = userMapper.update(null, userWrapper);
        System.out.println("影响记录数：" + rows);
    }

    @Test
    public void updateByWrapperLambda() {
        LambdaUpdateWrapper<User> lambdaUpdate = Wrappers.lambdaUpdate();
        // set 要修改的字段
        lambdaUpdate.eq(User::getName, "李艺伟").eq(User::getAge, 30).set(User::getAge, 31);

        int rows = userMapper.update(null, lambdaUpdate);
        System.out.println("影响记录数：" + rows);
    }

    /**
     *  ChainWrapper
     */
    @Test
    public void updateByWrapperLambdaChain() {
        boolean flag = new LambdaUpdateChainWrapper<User>(userMapper).eq(User::getName, "李艺伟").eq(User::getAge, 31)
                .set(User::getAge, 32).update();

        System.out.println("是否成功：" + flag);
    }


}
