package com.springcloud.ribbon.service.customer.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * #@EnableFeignClients开启spring cloud feign的支持,启动器一定要加@EnableFeignClients，代表进行Feign
 * #@EnableDiscoveryClient通过该注解，实现服务发现，注册-spring-cloud-commons，支持consul、zookeeper、eureka
 */
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class ComSpringcloudRibbonServiceCustomerFeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(ComSpringcloudRibbonServiceCustomerFeignApplication.class, args);
    }
}

