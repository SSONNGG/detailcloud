package com.yc.springcloud812;

import com.yc.springcloud812.robinConfiguration.RobinConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

import javax.sql.DataSource;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@EnableEurekaClient //启用Eureka客户端，这样能完成服务发现
//制定这个新的负载算法类给 MICROSERVICE-PROVIDER 这个服务用
//这里的name指服务的名称，如果需要多个服务提供方，这个时候可以使用@RibbonClient进行配置
//只对服务名为 MICROSERVICE-PROVIDER 下的服务清单采用 RobinConfigure.class 指定的随机负载均衡方式
//如有多个服务采用不同的策略，则采用@RibbonClients完成
@RibbonClient(name = "MICROSERVICE-PROVIDER",configuration = RobinConfigure.class)
public class MisConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MisConsumerApplication.class, args);
    }

}
