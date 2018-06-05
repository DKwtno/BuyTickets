package com.sorahjy.buytickets;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

// @SpringBootApplication
// @EnableEurekaClient
// @EnableCircuitBreaker
@SpringCloudApplication
@EnableFeignClients
public class BuyticketsApplication {

    public static void main(String[] args) {
        SpringApplication.run(BuyticketsApplication.class, args);
    }
}
