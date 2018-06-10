package com.sorahjy.buytickets;

import org.springframework.boot.SpringApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

// @SpringBootApplication
// @EnableEurekaClient
// @EnableCircuitBreaker
@SpringCloudApplication
@EnableFeignClients
@EnableCaching
public class USSTALApplication {

    public static void main(String[] args) {
        SpringApplication.run(USSTALApplication.class, args);
    }
}
