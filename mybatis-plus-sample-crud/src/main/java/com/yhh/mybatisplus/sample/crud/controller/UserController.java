package com.yhh.mybatisplus.sample.crud.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yhh.mybatisplus.sample.crud.entity.User;
import com.yhh.mybatisplus.sample.crud.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Author: -小野猪-
 * @Date: 2019-05-28 14:13
 * @Version: 1.0
 * @Desc:
 */
@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;


    @RequestMapping(value = "/list")
    public Object list() {
        List<User> users = userMapper.selectList(null);
        return users;
    }

    @RequestMapping(value = "/insert")
    public Object insert() {
        // 生成的id 好大啊  1133612014182965249
        User user = new User();
        user.setAge(11);
        user.setEmail("email");
        user.setUsername("1111");
        int c = userMapper.insert(user);
        return c;
    }

    @RequestMapping(value = "/delete")
    public Object delete() {
        int c = userMapper.deleteById(1L);
        System.out.println(c);

        int i = userMapper.delete(new QueryWrapper<User>().lambda().eq(User::getUsername, "1111"));
        System.out.println(i);
        return c;
    }

    @RequestMapping(value = "/update")
    public Object update() {
        int c = userMapper.updateById(new User().setId(1L).setEmail("ab@c.c"));
        System.out.println(c);

        // 所有的记录都修改了   bug
        int i = userMapper.update(new User().setUsername("update"), Wrappers.<User>lambdaUpdate().set(User::getAge, 11));
        System.out.println(i);
        return c;
    }


    @RequestMapping(value = "/select")
    public Object select() {
        User user = userMapper.selectById(1L);
        System.out.println(user);

        User user1 = userMapper.selectOne(new QueryWrapper<User>().lambda().eq(User::getId, 1L));
        System.out.println(user1);
        return user1;
    }

    @RequestMapping(value = "/order")
    public Object order() {
        List<User> users = userMapper.selectList(Wrappers.<User>query().orderByAsc("age"));

        List<Map<String, Object>> mapList = userMapper.selectMaps(Wrappers.<User>query().orderByAsc("age"));
        System.out.println(mapList);

        IPage<Map<String, Object>> page = userMapper.selectMapsPage(new Page<>(1, 5), Wrappers.<User>query().orderByAsc("age"));
        System.out.println(page);


        List<User> users2 = userMapper.selectList(Wrappers.<User>lambdaQuery().orderByAsc(User::getAge));
        System.out.println(users2);

        return users;
    }


}
