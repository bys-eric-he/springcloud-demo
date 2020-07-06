package com.springcloud.api.gateway.comspringcloudapigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix
public class ComSpringcloudApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ComSpringcloudApiGatewayApplication.class, args);
    }

    /**
     * 编程式流式配置添加熔断器处理及API路由
     *
     * @param builder
     * @return
     */
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        String uri = "lb://ribbon-service";
        return builder.routes()

                .route("spring-cloud-service-first", p -> p
                        .path("/api/**")
                        .filters(f -> f
                                        .rewritePath("/api/(?<segment>.*)", "/api/${segment}")
                                        .addRequestHeader("token", "2020HDFSAHGFD")
                                        .addRequestParameter("name", "he.yong")
                                        .hystrix(config -> config
                                                .setName("fallback")
                                                .setFallbackUri("forward:/fallback/second"))

                                //限流
                                /*.requestRateLimiter(r -> r.setRateLimiter(redisRateLimiter()))*/)
                        .uri(uri))

                .route("spring-cloud-service-second", p -> p
                        .path("/api/**")
                        .filters(f -> f
                                        .rewritePath("/api/(?<segment>.*)", "/api/${segment}")
                                        .addRequestHeader("token", "2020@1990@ERIC&HE")
                                        .addRequestParameter("name", "eric.he")
                                        .hystrix(config -> config
                                                .setName("fallback")
                                                .setFallbackUri("forward:/fallback/first"))

                                //限流
                                /*.requestRateLimiter(r -> r.setRateLimiter(redisRateLimiter()))*/)
                        .uri(uri))
                .build();

    }

    /*
    @Bean
    public RedisRateLimiter redisRateLimiter() {
        return new RedisRateLimiter(2, 4);
    }*/
}
