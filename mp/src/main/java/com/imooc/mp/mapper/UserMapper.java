package com.imooc.mp.mapper;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.imooc.mp.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface UserMapper extends BaseMapper<User> {

    // 优先使用xml中的
    @Select({"select * from user ${ew.customSqlSegment}"})
    @Results(id = "xxx", value = {
            @Result(column = "id", property = "id", id = true),
            @Result(column = "name", property = "name"),
            @Result(column = "age", property = "age"),
            @Result(column = "email", property = "email"),
            @Result(column = "manager_id", property = "managerId"),
            @Result(column = "create_time", property = "createTime")
    })
    List<User> selectAll(@Param(Constants.WRAPPER) Wrapper<User> wrapper);


    IPage<User> selectUserPage(Page<User> page, @Param(Constants.WRAPPER) Wrapper<User> wrapper);

}