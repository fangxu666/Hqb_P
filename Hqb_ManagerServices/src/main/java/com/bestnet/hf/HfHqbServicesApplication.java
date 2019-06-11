package com.bestnet.hf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
//提供服务端向Eureka服务端注册
@EnableEurekaClient
public class HfHqbServicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(HfHqbServicesApplication.class, args);
    }

}