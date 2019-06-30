package com.yhh.mybatis.dao;

import com.yhh.mybatis.entity.User;

import java.util.List;

/**
 * @Author: -小野猪-
 * @Date: 2019/6/29 23:59
 * @Version: 1.0
 * @Desc:
 */
public interface UserDao {


    /**
     * 一般都会加抛异常 增加系统的健壮性
     */

    //根据id查询用户信息
    User findUserById(int id) throws Exception;

    //根据用户名列查询用户列表
    List<User> findUserByName(String name) throws Exception;

    //添加用户信息
    int insertUser(User user) throws Exception;


    //删除用户信息
    int deleteUser(int id) throws Exception;

    //更新用户信息
    int updateUser(User user) throws Exception;

}
