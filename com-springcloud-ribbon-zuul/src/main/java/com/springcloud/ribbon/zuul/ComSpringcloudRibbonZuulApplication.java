package com.springcloud.ribbon.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * 不需要对ZUUL服务作任何代码上的编写, 只需要将自身作为服务注册到Eureka注册中心即可
 * 注册后Zuul将默认从Eureka服务器中获取所注册的服务，然后将相应的服务注册到Eureka中的名称作为请求路径中的一部份
 * 用户通过Zuul网关端口请求的时候, 请求就会自动转发到这些服务中。
 *
 * 比如我们在没有引入API网关的时候，调用 customer-feign 服务的API时,通过 http://localhost:8085/api/feign-customer/hi 调用
 * 引入API网关后 通过 http://localhost:8280/customer-feign/api/feign-customer/hi 调用
 */
@SpringBootApplication
@EnableZuulProxy
public class ComSpringcloudRibbonZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComSpringcloudRibbonZuulApplication.class, args);
	}

}
