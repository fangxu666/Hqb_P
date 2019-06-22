package com.bestnet.hf.config;

import com.bestnet.hf.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    private final String [] notLoginInterceptPaths = {"/login/**","/static/**","/images/**","/css/**","/js/**","/font/**","/layui.all.js"};

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //addPathPatterns 需要拦截的请求；excludePathPatterns无需拦截的请求
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**")
                .excludePathPatterns(notLoginInterceptPaths);
    }
}
