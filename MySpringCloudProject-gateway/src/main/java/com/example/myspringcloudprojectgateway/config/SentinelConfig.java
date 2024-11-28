package com.example.myspringcloudprojectgateway.config;

import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.GatewayCallbackManager;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.example.myspringcloudprojectgateway.ResponseMsg;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.util.Objects;

@Configuration
public class SentinelConfig {
    @PostConstruct
    public void init() {
        GatewayCallbackManager.setBlockHandler(((serverWebExchange, throwable) -> {
            HttpStatusCode code = serverWebExchange.getResponse().getStatusCode();
            BlockException blockException = (BlockException) throwable;

            ResponseMsg body = new ResponseMsg(
                    Objects.requireNonNull(serverWebExchange.getResponse().getStatusCode()).value(),
                    blockException.getRule().toString(),
                    blockException.getClass().getName()
            );

            if (code != null) {
                return ServerResponse
                        .status(code)
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(body);
            }
            return null;
        }));
    }
}
