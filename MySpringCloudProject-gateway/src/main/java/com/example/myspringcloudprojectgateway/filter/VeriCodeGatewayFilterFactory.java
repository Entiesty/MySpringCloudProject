package com.example.myspringcloudprojectgateway.filter;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Component
public class VeriCodeGatewayFilterFactory
        extends AbstractGatewayFilterFactory<VeriCodeGatewayFilterFactory.Config> {

    private final StringRedisTemplate stringRedisTemplate;

    public VeriCodeGatewayFilterFactory(StringRedisTemplate stringRedisTemplate) {
        super(Config.class);
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return List.of("prefix", "consoleOut");
    }


    @Override
    public GatewayFilter apply(Config config) {
        return (((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            MultiValueMap<String, String> params = request.getQueryParams();
            String userId = params.getFirst("user_id");
            String veriCodeParam = params.getFirst("veri_code");
            String veriCode = stringRedisTemplate.opsForValue().get(config.getPrefix() + "_" + userId);

            if (veriCodeParam != null && veriCodeParam.equals(veriCode)) {
                if (config.isConsoleOut()) {
                    System.out.println("验证码一致，即将转发请求......");
                }
                return chain.filter(exchange);
            }

            if (config.isConsoleOut()) {
                System.out.println("验证码不一致，不转发请求......");
            }

            ServerHttpResponse response = exchange.getResponse();
            Map<String, String> result = Map.of("id", "401", "msg", "验证码不一致");
            String body = JSONObject.toJSONString(result);
            DataBuffer buffer = response.bufferFactory().wrap(body.getBytes());
            response.getHeaders().add("Content-Type", "application/json; charset=utf-8");
            response.setRawStatusCode(HttpStatus.UNAUTHORIZED.value());

            return response.writeWith(Mono.just(buffer));
        }));
    }


    @Data
    public static class Config {
        private boolean consoleOut;
        private String prefix;
    }
}
