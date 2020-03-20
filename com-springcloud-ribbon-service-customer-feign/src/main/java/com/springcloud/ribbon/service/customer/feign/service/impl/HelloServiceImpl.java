package com.springcloud.ribbon.service.customer.feign.service.impl;

import com.springcloud.ribbon.service.customer.feign.service.HelloService;
import org.springframework.stereotype.Service;

/**
 * Feign容错降级处理类，它并不是Spring的Service类，
 * Feign会扫描标有@FeignClient注解的接口，生成代理
 * HelloServiceImpl仅仅是被指定的降级处理类
 */
@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public String hello() {
        return "请求错误!远端服务不可达,熔断错误回调方法返回结果!";
    }

    /**
     * 发现这里的入参里我故意去掉了@RequestParam、@RequestBody、@RequestHeader注解，
     * 因为这几个注解本质上的意义就在于Feign在做微服务调用的时候对http传递参数用的，
     * 但服务降级根本不会做http请求了，所以此处可以省略
     */
    @Override
    public String hello(String name, String password) {
        return "无可用远端服务, 此结果为本地容错降级返回...";
    }
}
