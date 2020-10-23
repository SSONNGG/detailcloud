package com.yc.springcloud812.controller;


import com.yc.springcloud812.main.Book;
import com.yc.springcloud812.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("book") //http://localhost;8888/book/xxx
public class BookController {
    //注入service
    @Autowired
    private BookService bookService;
    //spring DI
    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping("{id}")
    public Book getBook(@PathVariable("id")Integer id){
        show();
        return bookService.getBook(id);
    }
    @GetMapping("findAll")
    public List<Book> getAll(){
        show();
        return bookService.findAll();
    }

    private void show(){
        System.out.println( this.discoveryClient.description() );

        //EurekaDiscoveryClient
        EurekaDiscoveryClient edc=(EurekaDiscoveryClient)this.discoveryClient;
        // 服务器上有有很多服务
        List<String> servicesName=edc.getServices();
        for( String sn:servicesName ){
            System.out.println("服务名:"+ sn );
            List<ServiceInstance> instances=edc.getInstances(  sn );
            for(  ServiceInstance si:instances ){
                System.out.println(  si.getScheme()+" "+  si.getHost() +" "+si.getPort()+" "+ si.getUri()  );
            }
        }
    }
}