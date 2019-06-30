package com.yhh.mybatis.mapper;

import com.yhh.mybatis.entity.User;
import com.yhh.mybatis.vo.UserCustom;
import com.yhh.mybatis.vo.UserQueryVo;

import java.util.List;

/**
 * @Author: -小野猪-
 * @Date: 2019/6/30 10:35
 * @Version: 1.0
 * @Desc:
 */
public interface UserMapper {


    //用户信息综合查询
    List<UserCustom> findUserList(UserQueryVo userQueryVo) throws Exception;

    //根据id查询用户信息，使用resultMap输出
    User findUserByIdResultMap(int id) throws Exception;

}
