package com.yhh.mybatisplus.sample.crud.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author miemie
 * @since 2018-08-10
 */
@Configuration
@MapperScan("com.yhh.mybatisplus.sample.crud.mapper")
public class MybatisPlusConfig {

}
