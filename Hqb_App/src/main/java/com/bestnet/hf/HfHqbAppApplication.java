package com.bestnet.hf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
//Eureka客户端 消费者
@EnableEurekaClient
public class HfHqbAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(HfHqbAppApplication.class, args);
    }

}
