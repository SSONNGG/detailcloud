package com.yc.springcloud812;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import javax.sql.DataSource;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@EnableEurekaClient //启用Eureka客户端，这样能完成服务发现
public class MisConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MisConsumerApplication.class, args);
    }

}
