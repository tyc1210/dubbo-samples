package com.tyc.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 自定义网关 filter
 */
@Configuration
@Slf4j
public class AuthGlobalFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // todo 认证鉴权
        log.info("进入自定义filter");
        if(exchange.getRequest().getQueryParams().get("username") != null){
            log.info("合法用户");
            return chain.filter(exchange);
        }
        log.info("非法用户");
        return exchange.getResponse().setComplete();
    }

    @Override
    public int getOrder() {
        // 控制filter则行顺序 数字越小越先执行
        return -1;
    }
}
