package com.imooc.mp.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;


/**
 * @Author: -小野猪-
 * @Date: 2019-06-29 0:14
 * @Version: 1.0
 * @Desc:
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("user")
public class User extends Model<User>
    {

    private static final long serialVersionUID = 2603890837103787306L;

    /**
     * 主键   mp的id 默认策略是 基于雪花算法自增
     * id 默认策略  IdType.NONE
     */
    //@TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 姓名
     * strategy = FieldStrategy.DEFAULT   实体属性为null或空字符串 是否会插入
     */
    @TableField(value = "name", condition = SqlCondition.LIKE, strategy = FieldStrategy.DEFAULT)
    private String name;
    //private String realName;    // mp 默认开启 实体类驼峰 对应 数据库 字段下划线

    /**
     * 年龄
     */
    @TableField(condition = "%s&lt;#{%s}")
    private Integer age;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 直属上级id
     */
    private Long managerId;   // mp 默认开启 实体类驼峰 对应 数据库 字段下划线

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /*
     * 备注（不与数据库字段对应） # transient 或 static 修饰 不与数据库字段映射
     */
    @TableField(exist = false)
    private String remark;

}

