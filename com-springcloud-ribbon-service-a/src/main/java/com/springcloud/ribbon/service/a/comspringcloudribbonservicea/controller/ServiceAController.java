package com.springcloud.ribbon.service.a.comspringcloudribbonservicea.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/service")
public class ServiceAController {
    @RequestMapping("/hello")
    public String testA(HttpServletRequest servletRequest) {
        String name = servletRequest.getParameter("name");
        String token = servletRequest.getHeader("token");

        System.out.println("--------------------parameter[{name=" + name + "}], token[{value=" + token + "}]-----------------");

        return "Hello world Service -->AA.AA.AA.AA.AA!";
    }
}
