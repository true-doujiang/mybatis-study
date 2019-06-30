package com.yhh.mybatis.mapper;

import com.yhh.mybatis.entity.User;

/**
 * @Author: -小野猪-
 * @Date: 2019/6/30 13:19
 * @Version: 1.0
 * @Desc:
 */
public interface UserMapper {

    //根据id查询用户信息       产生的动态代理对象调用selectOne方法(根据返回值判断的)
    User findUserById(int id) throws Exception;
}
