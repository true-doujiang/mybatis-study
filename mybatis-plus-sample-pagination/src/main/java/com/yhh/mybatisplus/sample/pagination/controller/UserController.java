package com.yhh.mybatisplus.sample.pagination.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yhh.mybatisplus.sample.pagination.entity.User;
import com.yhh.mybatisplus.sample.pagination.mapper.UserMapper;
import com.yhh.mybatisplus.sample.pagination.model.MyPage;
import com.yhh.mybatisplus.sample.pagination.model.ParamSome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSON;
import java.util.List;

/**
 * @Author: -小野猪-
 * @Date: 2019-05-29 20:00
 * @Version: 1.0
 * @Desc:
 */
@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;



    @GetMapping(value = "/test")
    public IPage<User> test() {
        System.out.println("----- baseMapper 自带分页 ------");
        Page<User> page = new Page<>(1, 5);
        IPage<User> userIPage = userMapper.selectPage(page, new QueryWrapper<User>().eq("age", 20).eq("username", "Jack"));
        //assertThat(page).isSameAs(userIPage);

        System.out.println("总条数 ------> " + userIPage.getTotal());
        System.out.println("当前页数 ------> " + userIPage.getCurrent());
        System.out.println("当前每页显示数 ------> " + userIPage.getSize());
        print(userIPage.getRecords());
        System.out.println("----- baseMapper 自带分页 ------");



        System.out.println("json 正反序列化 begin");
        String json = JSON.toJSONString(page);
        System.out.println(json);
//        Page<User> page1 = JSON.parseObject(json, TypeBuilder.newInstance(Page.class).addTypeParam(User.class).build());
//        print(page1.getRecords());
        System.out.println("json 正反序列化 end");



        System.out.println("----- 自定义 XML 分页 ------");
        MyPage<User> myPage = new MyPage<User>(1, 5).setSelectInt(20).setSelectStr("Jack");
        ParamSome paramSome = new ParamSome(20, "Jack");
        MyPage<User> userMyPage = userMapper.mySelectPage(myPage, paramSome);
        //assertThat(myPage).isSameAs(userMyPage);

        System.out.println("总条数 ------> " + userMyPage.getTotal());
        System.out.println("当前页数 ------> " + userMyPage.getCurrent());
        System.out.println("当前每页显示数 ------> " + userMyPage.getSize());
        print(userMyPage.getRecords());
        System.out.println("----- 自定义 XML 分页 ------");


        return userIPage;
    }

    private <T> void print(List<T> list) {
        if (!CollectionUtils.isEmpty(list)) {
            list.forEach(System.out::println);
        }
    }


}
