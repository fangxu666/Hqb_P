package com.bestnet.hf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
//Eureka客户端 消费者
@EnableEurekaClient
//服务发现
@EnableFeignClients
@EnableDiscoveryClient
public class HfHqbAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(HfHqbAppApplication.class, args);
    }

}
