package com.springcloud.ribbon.service.a.comspringcloudribbonservicea;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 该注解会根据配置文件中的地址，将服务自身注册到服务注册中心
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ComSpringcloudRibbonServiceAApplication {

    public static void main(String[] args) {
        SpringApplication.run(ComSpringcloudRibbonServiceAApplication.class, args);
    }

}

