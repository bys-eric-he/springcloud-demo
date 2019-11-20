package com.springcloud.ribbon.service.customer.controller;

import com.springcloud.ribbon.service.customer.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    HelloService service;

    @RequestMapping("/hi")
    public String customer(){
        return service.helloService();
    }
}
