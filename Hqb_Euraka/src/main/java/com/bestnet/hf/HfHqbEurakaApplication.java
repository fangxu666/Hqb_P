package com.bestnet.hf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
//启动一个服务注册中心提供给其他应用进行对话
@EnableEurekaServer
public class HfHqbEurakaApplication {

    public static void main(String[] args) {
        SpringApplication.run(HfHqbEurakaApplication.class, args);
    }

}
