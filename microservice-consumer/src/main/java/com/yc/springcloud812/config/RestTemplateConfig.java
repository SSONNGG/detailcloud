package com.yc.springcloud812.config;


import com.netflix.loadbalancer.IRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.Base64;

//一个配置类:由spring托管
@Configuration
public class RestTemplateConfig {
    //--> Spring容器托管  “restTemplate”   RestTemplate对象

    @Bean     //少了 @LoadBalance
    @LoadBalanced //加入负载均衡  此处的负载均衡是全局配置
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public HttpHeaders getHeaders(){
        HttpHeaders headers=new HttpHeaders();
        String auth="admin:a";   //认证的原始用户名和密码
        byte[] encodeAuth= Base64.getEncoder().encode(auth.getBytes(Charset.forName("US-ASCII"))); //加密处理
        String authHeader="Basic "+new String(encodeAuth);
        headers.set("Authorization",authHeader);    //    Http请求头         Authorization: Base xxxxxxxxx
        System.err.println(headers);
        return headers;
    }

//    @Bean   //这个会由spring托管，则这个消费端的微服务都会采用这种负载均衡
//    public IRule ribbonRule(){  //其中IRule就是所有规则的标准
//        return new com.netflix.loadbalancer.RandomRule();   //随机的访问策略
//    }

}