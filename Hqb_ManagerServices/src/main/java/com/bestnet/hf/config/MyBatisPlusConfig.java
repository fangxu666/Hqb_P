package com.bestnet.hf.config;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 说明：MyBatisplus配置类
 *
 * 作者：hzg
 *
 * 时间：2019-06-11
 *
 * */
@Configuration
@MapperScan("com.bestnet.hf.mapper")
public class MyBatisPlusConfig {
    // mybatis-plus分页插件
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
