package com.springcloud.api.gateway.comspringcloudapigateway.filter;

import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.function.Consumer;

/**
 * 全局过滤器
 */
@Component
public class AuthSignatureFilter implements GlobalFilter, Ordered {
    public final static String ATTRIBUTE_IGNORE_GLOBAL_FILTER = "@ignoreGlobalFilter";

    /**
     * 拦截请求，获取authToken，校验
     *
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //从消息头中获取token，根据业务需要进行设置吧
        String token = exchange.getRequest().getHeaders().getFirst("token");
        Consumer<HttpHeaders> httpHeaders = httpHeader -> {
            //获取ip放入请求头
            String ip = exchange.getRequest().getRemoteAddress() != null ? exchange.getRequest().getRemoteAddress().getAddress().getHostAddress() : null;
            //这里这样做是为了下游可以从消息头获取到对应参数
            httpHeader.set("USER-AUTHENTICITY-IP", ip);
            System.out.println("=========httpHeader=" + httpHeader + "=================");
            httpHeader.set("token", token);
        };
        //打印参数的封装map
        HashMap<String, Object> data = new HashMap<>();
        // 部分路由不进行token验证
        if (exchange.getAttribute(ATTRIBUTE_IGNORE_GLOBAL_FILTER) != null) {
            System.out.println("IgnoreGlobalFilter:" + exchange.getRequest().getPath().value());
            ServerHttpRequest serverHttpRequest = exchange.getRequest();
            return chain.filter(exchange.mutate().request(serverHttpRequest).build());
        }

        //token验证
        ServerHttpRequest serverHttpRequest = exchange.getRequest();
        String method = serverHttpRequest.getMethodValue();

        serverHttpRequest = exchange.getRequest().mutate().headers(httpHeaders).build();
        String serviceMode = serverHttpRequest.getQueryParams().getFirst("mode");
        data.put("token", token);
        data.put("url", serverHttpRequest.getPath().value());
        data.put("mode", "parseToken");
        if ("GET".equals(method) || StringUtils.isNotBlank(serviceMode)) {
            data.put("serviceMode", serviceMode);
            //模拟某些系连接需要被禁止：比如验证token
            if ("forbidn".equals(token)) {
                System.out.println("get:" + serverHttpRequest.getPath().value() + "--" + serviceMode);
                System.out.println("==============请====================");
                System.out.println("================求==================");
                System.out.println("=================被=================");
                System.out.println("===================禁===============");
                System.out.println("=====================止=============");
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }
            return chain.filter(exchange.mutate().request(serverHttpRequest).build());
        } else if ("POST".equals(method)) {
            return DataBufferUtils.join(exchange.getRequest().getBody())
                    .flatMap(dataBuffer -> {
                        byte[] bytes = new byte[dataBuffer.readableByteCount()];
                        dataBuffer.read(bytes);
                        //==========================================
                        //和get请求一样进行token验证
                        //这里只对get和post请求进行了处理，只是演示使用；不具备生产环境使用，
                        // 请慎重！！！
                        // 请慎重！！！
                        //==========================================
                        DataBufferUtils.release(dataBuffer);
                        Flux<DataBuffer> cachedFlux = Flux.defer(() -> {
                            DataBuffer buffer = exchange.getResponse().bufferFactory()
                                    .wrap(bytes);
                            return Mono.just(buffer);
                        });

                        ServerHttpRequest mutatedRequest = new ServerHttpRequestDecorator(
                                exchange.getRequest()) {
                            @Override
                            public Flux<DataBuffer> getBody() {
                                return cachedFlux;
                            }
                        };
                        mutatedRequest = mutatedRequest.mutate().headers(httpHeaders).build();
                        System.out.println("post:" + mutatedRequest.getPath().value() + "--" + data.get("serviceMode"));
                        return chain.filter(exchange.mutate().request(mutatedRequest)
                                .build());
                    });
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
