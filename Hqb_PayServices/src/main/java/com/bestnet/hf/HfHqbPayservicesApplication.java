package com.bestnet.hf;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
//提供服务端向Eureka服务端注册
@EnableEurekaClient
//对hystrixR熔断机制的支持
@EnableCircuitBreaker
//开启分布式事务注解
@EnableDistributedTransaction
public class HfHqbPayservicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(HfHqbPayservicesApplication.class, args);
    }

}
