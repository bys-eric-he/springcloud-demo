package com.springcloud.ribbon.service.b.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/service")
public class ServiceBController {
    @RequestMapping("/hello")
    public String testB() {
        return "Hello world Service BBBBBBB!";
    }
}
