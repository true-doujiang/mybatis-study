package com.yhh.mybatisplus.sample.quickstart.controller;

import com.yhh.mybatisplus.sample.quickstart.entity.User;
import com.yhh.mybatisplus.sample.quickstart.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: -小野猪-
 * @Date: 2019-05-27 18:45
 * @Version: 1.0
 * @Desc:
 */
@RestController
public class UserController {


    @Autowired
    private UserMapper userMapper;

    @GetMapping(value = "list")
    public Object list() {
        List<User> users = userMapper.selectList(null);
        return users;
    }
}
