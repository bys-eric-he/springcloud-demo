package com.springcloud.ribbon.service.customer.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HelloService {

    /**
     * 负载均衡ribbon对象
     */
    @Autowired
    RestTemplate restTemplate;

    /**
     * 熔断错误回调方法
     *
     * @return
     */
    public String helloFallBack() {
        return "Error occurred!";
    }

    /**
     * 调用Eureka系统中名都为test-service的ribbon_service_a或ribbon_service_b的方法/hello
     * 注解指定发生错误时的回调方法
     *
     * @return
     */
    @HystrixCommand(fallbackMethod = "helloFallBack")
    public String helloService() {
        // Get请求调用服务，restTemplate被@LoadBalanced注解标记，Get方法会自动进行负载均衡
        // restTemplate会交替调用service_a和service_b
        return restTemplate.getForObject("http://ribbon-service/api/service/hello", String.class);
    }
}
