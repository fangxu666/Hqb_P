package com.bestnet.hf.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
/**
 * 说明：启动加载项
 *
 * 作者：hzg
 *
 * 时间：2019-06-11
 *
 * */
@Configuration
public class BeanConfig {
    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
