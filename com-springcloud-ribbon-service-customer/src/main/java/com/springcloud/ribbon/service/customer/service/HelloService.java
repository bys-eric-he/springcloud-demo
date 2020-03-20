package com.springcloud.ribbon.service.customer.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
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
     * 1.降级是为了更好的用户体验，当一个方法调用异常时，通过执行另一种代码逻辑来给用户友好的回复。
     * 2.熔断是服务雪崩的一种有效解决方案。当指定时间窗内的请求失败率达到设定阈值时，系统将通过断路器直接将此请求链路断开。
     * @return
     */
    public String helloFallBack() {
        return "远端服务不可用,熔断错误回调方法返回结果!";
    }

    /**
     * 调用Eureka系统中名都为test-service的ribbon_service_a或ribbon_service_b的方法/hello
     * 注解指定发生错误时的回调方法
     * fallbackMethod 指定了后备方法调用
     *
     * Hystrix降级处理超时时间设置execution.isolation.thread.timeoutInMilliseconds，默认情况下调用接口能够触发Hystrix服务降级处理的超时时间是1000ms
     * @return
     */
    @HystrixCommand(fallbackMethod = "helloFallBack",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1200")
            })
    public String helloService() {
        // Get请求调用服务，restTemplate被@LoadBalanced注解标记，Get方法会自动进行负载均衡
        // restTemplate会交替调用service_a和service_b
        return restTemplate.getForObject("http://ribbon-service/api/service/hello", String.class);
    }
}
