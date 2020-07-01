package com.springcloud.eureka.register.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 开启eureka服务,通过@EnableEurekaServer注解来开启服务
 */
@EnableEurekaServer
@SpringBootApplication
public class ComSpringcloudEurekaRegisterServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ComSpringcloudEurekaRegisterServiceApplication.class, args);
    }
}

