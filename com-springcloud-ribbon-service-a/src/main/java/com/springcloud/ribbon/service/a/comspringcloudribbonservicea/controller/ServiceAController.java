package com.springcloud.ribbon.service.a.comspringcloudribbonservicea.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/service")
public class ServiceAController {
    @RequestMapping("/hello")
    public String testA() {
        return "Hello world Service AAAAAAAAAA!";
    }
}
