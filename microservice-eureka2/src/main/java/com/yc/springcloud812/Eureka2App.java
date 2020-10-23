package com.yc.springcloud812;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer   //启用 eureka服务器
public class Eureka2App {
    public static void main(String[] args) {
        SpringApplication.run(  Eureka2App.class, args );
    }
}
