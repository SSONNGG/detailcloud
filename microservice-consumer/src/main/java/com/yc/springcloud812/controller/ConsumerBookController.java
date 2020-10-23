package com.yc.springcloud812.controller;



import com.yc.springcloud812.main.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/consumer")
public class ConsumerBookController {

    //private final static  String URL="http://localhost:8888/book/";+
    //直接访问eureka中的服务名即可,这样 ribbon会拉取到服务实例列表，再利用负载均衡算法获取一个服务.
    private final static String RESTURI="http://MICROSERVICE-PROVIDER/";
    //                                   http://ip:port/

    @Resource
    private LoadBalancerClient loadBalancerClient;  //服务提供方法信息
                                                    //因为ribbon是客户端的负载均衡，所以可以在客户端记录访问的日志


    @Autowired
    private RestTemplate restTemplate;


    @Autowired
    private HttpHeaders httpHeaders;

    @GetMapping("{id}")
    public Book getBook(@PathVariable("id") Integer id){
        //return restTemplate.getForObject(URL+id,Book.class);
        //   HttpEntity(    MultiValueMap)   ,    HttpHeaders: MultiValueMap
        //   返回: ResponseEntity
        //根据服务名获得服务清单
        ServiceInstance serviceInstance =this.loadBalancerClient.choose("MICROSERVICE-PROVIDER");
        String ip= serviceInstance.getHost();;
        int port=serviceInstance.getPort();

        System.out.println("【*** 服务提供实例信息 ***】host = "+ip
                            +";port = "+port
                            +";serviceId = "+serviceInstance.getServiceId());
        return restTemplate.exchange( RESTURI+"book/"+id, HttpMethod.GET, new HttpEntity<Object>(  httpHeaders   ), Book.class ).getBody();

       // return restTemplate.exchange( "http://"+ip+":"+port+"/"+"book/", HttpMethod.GET, new HttpEntity<Object>(  httpHeaders   ), Book.class ).getBody();
    }

    @GetMapping("/findAll")
    public List<Book> findAll( ){
        // return restTemplate.getForObject(URL+"findAll",   List.class);
        return restTemplate.exchange(RESTURI+"book/"+"findAll",HttpMethod.GET, new HttpEntity<Object>(  httpHeaders ), List.class).getBody();
    }






}