package com.yhh.mybatis.mapper;

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

    //用户信息综合查询总数
    int findUserCount(UserQueryVo userQueryVo) throws Exception;

}
