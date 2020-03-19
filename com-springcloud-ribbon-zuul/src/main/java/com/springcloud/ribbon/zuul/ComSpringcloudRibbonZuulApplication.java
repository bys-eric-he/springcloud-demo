package com.springcloud.ribbon.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * ZUUL 是从设备和 web 站点到 Netflix 流应用后端的所有请求的前门。
 * 作为边界服务应用，ZUUL 是为了实现动态路由、监视、弹性和安全性而构建的。
 *
 * 不需要对ZUUL服务作任何代码上的编写, 只需要将自身作为服务注册到Eureka注册中心即可
 * 注册后Zuul将默认从Eureka服务器中获取所注册的服务，然后将相应的服务注册到Eureka中的名称作为请求路径中的一部份
 * 用户通过Zuul网关端口请求的时候, 请求就会自动转发到这些服务中。
 *
 * 即[Eureka] Server是服务提供者的统一入口。
 * 那么整个应用中存在那么多消费者需要用户进行调用，这个时候用户该怎样访问这些消费者工程呢？
 * 当然可以像之前那样直接访问这些工程。但这种方式没有统一的消费者工程调用入口，不便于访问与管理，
 * 而 Zuul 就是这样的一个对于消费者的统一入口。
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
