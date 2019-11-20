package com.springcloud.ribbon.service.b;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 该注解会根据配置文件中的地址，将服务自身注册到服务注册中心
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ComSpringcloudRibbonServiceBApplication {

    public static void main(String[] args) {
        SpringApplication.run(ComSpringcloudRibbonServiceBApplication.class, args);
    }

}

