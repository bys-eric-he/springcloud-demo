package com.springcloud.ribbon.service.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 开启断路器功能，进行容错管理
 * 开启服务发现功能
 */
@EnableCircuitBreaker
@EnableDiscoveryClient
@SpringBootApplication
public class ComSpringcloudRibbonServiceCustomerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ComSpringcloudRibbonServiceCustomerApplication.class, args);
    }

    /**
     * 注册一个具有容错功能的RestTemplate
     * 开启负载均衡客户端
     *
     * @return
     */
    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

