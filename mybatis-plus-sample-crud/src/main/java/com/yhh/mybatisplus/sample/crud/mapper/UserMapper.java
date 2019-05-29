package com.yhh.mybatisplus.sample.crud.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yhh.mybatisplus.sample.crud.entity.User;

/**
 * @Author: -小野猪-
 * @Date: 2019-05-28 14:10
 * @Version: 1.0
 * @Desc:
 * <p>
 * MP 支持不需要 UserMapper.xml 这个模块演示内置 CRUD 咱们就不要 XML 部分了
 * </p>
 */
public interface UserMapper extends BaseMapper<User> {
}
