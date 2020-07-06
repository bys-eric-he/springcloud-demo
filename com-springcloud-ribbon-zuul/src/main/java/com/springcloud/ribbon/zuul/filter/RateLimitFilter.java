package com.springcloud.ribbon.zuul.filter;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 通过Zuul的前置过滤器来实现一下令牌桶限流。
 *
 */
@Order(value = 3)
@Component
public class RateLimitFilter extends ZuulFilter {
    // 定义一个令牌桶，每秒产生2个令牌，即每秒最多处理2个请求
    // 这样我们就能将请求数量控制在一秒两个，有没有觉得很酷？
    private static final RateLimiter RATE_LIMITER = RateLimiter.create(2);

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return -5;
    }

    @Override
    public Object run() throws ZuulException {
        System.out.println("放行");
        return null;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext context = RequestContext.getCurrentContext();
        if (RATE_LIMITER.tryAcquire()) {
            return true;
        }
        System.out.println("访问量超载");
        // 指定当前请求未通过过滤
        context.setSendZuulResponse(false);
        // 向客户端返回响应码429，请求数量过多
        context.setResponseStatusCode(429);
        return false;
    }
}