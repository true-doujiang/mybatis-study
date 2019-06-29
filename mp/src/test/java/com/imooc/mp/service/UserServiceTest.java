package com.imooc.mp.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.imooc.mp.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: -小野猪-
 * @Date: 2019-06-29 17:49
 * @Version: 1.0
 * @Desc:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;


    @Test
    public void getOne() {
        // 第二个参数： 大于一条记录时是否报错， false不报错，返回第一个   默认值true
        User one = userService.getOne(Wrappers.<User>lambdaQuery().gt(User::getAge, 25), false);
        System.out.println(one);
    }

    @Test
    public void insertBatch() {
        User user1 = new User();
        user1.setName("小可爱");
        user1.setAge(28);
        user1.setManagerId(1088248166370832385L);

        User user2 = new User();
        user2.setName("大傻瓜");
        user2.setAge(19);
        user2.setManagerId(1088248166370832385L);

        List<User> userList = Arrays.asList(user1, user2);

        /**
         * 循环插入，每次1000条
         */
        boolean flag = userService.saveBatch(userList, 1000);
        System.out.println("是否成功：" + flag);
    }

    @Test
    public void insertOrUpdateBatch() {
        User user1 = new User();
        user1.setName("李莫愁");
        user1.setAge(28);

        User user2 = new User();
        user2.setId(1134354221018144774L);
        user2.setName("张三丰");
        user2.setAge(20);
        user2.setManagerId(1088248166370832385L);

        List<User> userList = Arrays.asList(user1, user2);
        boolean flag = userService.saveOrUpdateBatch(userList);
        System.out.println("是否成功：" + flag);
    }

    @Test
    public void selectChain() {
        List<User> userList = userService.lambdaQuery().gt(User::getAge, 25).like(User::getName, "雨").list();
        userList.forEach(System.out::println);
    }

    @Test
    public void updateChain() {
        boolean flag = userService.lambdaUpdate().eq(User::getAge, 25).set(User::getAge, 26).update();
        System.out.println("是否成功：" + flag);
    }

    @Test
    public void removeChain() {
        boolean flag = userService.lambdaUpdate().eq(User::getAge, 20).remove();
        System.out.println("是否成功：" + flag);
    }

}
