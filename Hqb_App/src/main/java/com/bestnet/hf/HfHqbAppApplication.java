package com.bestnet.hf;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
//Eureka客户端 消费者
@EnableEurekaClient
//服务发现
@EnableFeignClients
@EnableDiscoveryClient
//开启redis session支持,并配置session过期时间
@EnableRedisHttpSession(maxInactiveIntervalInSeconds= 12000)
//开启分布式事务注解
@EnableDistributedTransaction
public class HfHqbAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(HfHqbAppApplication.class, args);
    }

}
