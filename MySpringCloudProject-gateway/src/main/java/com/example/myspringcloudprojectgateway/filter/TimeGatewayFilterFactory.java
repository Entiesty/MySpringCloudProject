package com.example.myspringcloudprojectgateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class TimeGatewayFilterFactory implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        long start = System.currentTimeMillis();
        Mono<Void> result = chain.filter(exchange);
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start) + "毫秒");

        return result;
    }
}
