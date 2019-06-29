package com.imooc.mp.mapper;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.imooc.mp.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.time.LocalDateTime;

/**
 * @Author: -小野猪-
 * @Date: 2019-06-29 16:59
 * @Version: 1.0
 * @Desc:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ARTest {

    /**
     * 1. 实体类 extends Model  实体类就要 CRUD 方法了
     * 2. 有可用的BaseMapper
     */

    @Test
    public void insert() {
        User user = new User();
        user.setName("丁茂义");
        user.setAge(40);
        user.setEmail("dmy@baomidou.com");
        user.setManagerId(1088248166370832385L);
        user.setCreateTime(LocalDateTime.now());

        boolean flag = user.insert();
        System.out.println("是否成功：" + flag);
    }

    @Test
    public void selectById1() {
        User user = new User();
        User userSelect = user.selectById(1134351388596264961L);

        System.out.println(userSelect == user);
        System.out.println(userSelect);
    }


    @Test
    public void selectOne() {
        User user = new User();
        // 多余一条默认返回 第一个，不报错。  打印警告日志
        User userSelect = user.selectOne(Wrappers.<User>query().like("name", "雨"));

        System.out.println(userSelect == user);
        System.out.println(userSelect);
    }


    @Test
    public void selectById2() {
        User user = new User();
        user.setId(1134351388596264961L);
        User userSelect = user.selectById();

        System.out.println(userSelect == user);
        System.out.println(userSelect);
    }

    @Test
    public void updateById() {
        User user = new User();
        user.setId(1134351388596264961L);
        user.setName("张无忌@@@");

        boolean flag = user.updateById();
        System.out.println("是否成功：" + flag);
    }

    @Test
    public void deleteById() {
        User user = new User();
        user.setId(1134351388596264961L);

        boolean flag = user.deleteById();
        System.out.println("是否成功：" + flag);
    }

    /**
     * 会发出2条sql 先查询有没有， 有就更新 没有就插入
     */
    @Test
    public void insertOrUpdate() {
        User user = new User();
        user.setId(1134354221018144770L);
        user.setAge(35);
        user.setCreateTime(LocalDateTime.now());

        boolean flag = user.insertOrUpdate();
        System.out.println("是否成功：" + flag);
    }


}
