package com.jfatty.zcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value = {"com.jfatty.zcloud" })
@EnableDiscoveryClient
@EnableFeignClients
public class ZcloudHealthServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZcloudHealthServiceApplication.class, args);
    }

}
