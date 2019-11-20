package com.springcloud.ribbon.service.customer.feign.service;

import com.springcloud.ribbon.service.customer.feign.service.impl.HelloServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * (1)
 * #@FeignClient注解定义了该接口是一个Feign客户端，name指定了注册到Eureka上的服务名，fallback是服务降级后的接口实现类
 * 为了让Feign知道在调用方法时应该向哪个地址发请求以及请求需要带哪些参数，我们需要定义一个接口
 * 用于通知Feign组件对该接口进行代理(不需要编写接口实现),ribbon-service为代理的具体服务
 * 这里的ribbon-service表示service_a和service_b注册到eureka的服务名
 * (2)
 * Spring Cloud Feign 的容错，HystrixCommand定义被封装起来，没有办法通过
 * #@HystrixCommand注解的fallback，指定服务降级（错误处理）方法,Spring Cloud Feign
 * 提供也更为简洁明了的方法实现服务降级处理,只需要编写一个feign接口的具体实现类即可并fallback
 * 指定对应降级处理类,Feign的模板化就体现在这里,通过Feign， 我们能把HTTP远程调用对开发者完全透明，
 * 得到与调用本地方法一致的编码体验
 * (3)
 * Spring Cloud应用在启动时，Feign会扫描标有@FeignClient注解的接口，生成代理，并注册到Spring容器中
 * 生成代理时Feign会为每个接口方法创建一个RequestTemplate对象,该对象封装了HTTP请求需要的全部信息，
 * 请求参数名、请求方法等信息,如果是在用Spring Cloud Netflix搭建微服务，那么Feign无疑是最佳选择
 */
@FeignClient(value = "ribbon-service", fallback = HelloServiceImpl.class)
public interface HelloService {
    /**
     * 对应具体服务中的接口地址,这里表是service-a和service-b
     * 对外提供的具体的接口服务,如com.easystudy.controller.ServiceAController的testA暴露的对外接口"/hello"
     *
     * @return
     */
    @RequestMapping(value = "/api/service/hello", method = RequestMethod.GET)
    String hello();

    @RequestMapping(value = "/api/service/hello2", method = RequestMethod.HEAD)
    String hello(@RequestHeader("name") String name,
                 @RequestHeader("password") String password);
}

