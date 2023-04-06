package com.tyc.gateway.filter;

import com.tyc.gateway.entity.TokenInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 自定义网关 filter
 */
@Configuration
@Slf4j
public class AuthGlobalFilter implements GlobalFilter, Ordered, InitializingBean {
    @Autowired
    private RestTemplate restTemplate;

    private static Set<String> shouldSkipUrl = new LinkedHashSet<>();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String requestPath = exchange.getRequest().getURI().getPath();
        //不需要认证的url
        if(shouldSkip(requestPath)) {
            return chain.filter(exchange);
        }
        //获取请求头
        String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");
        //请求头为空
        if(StringUtils.isEmpty(authHeader)) {
            throw new RuntimeException("请求头为空");
        }
        TokenInfo tokenInfo=null;
        try {
            //获取token信息
            tokenInfo = getTokenInfo(authHeader);
        }catch (Exception e) {
            throw new RuntimeException("校验令牌异常");
        }
        exchange.getAttributes().put("tokenInfo",tokenInfo);
        return chain.filter(exchange);
//        // todo 认证鉴权
//        log.info("进入自定义filter");
//        if(exchange.getRequest().getQueryParams().get("username") != null){
//            log.info("合法用户");
//            return chain.filter(exchange);
//        }
//        log.info("非法用户");
//        return exchange.getResponse().setComplete();
    }

    @Override
    public int getOrder() {
        // 控制filter则行顺序 数字越小越先执行
        return -1;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // 不拦截认证的请求
        shouldSkipUrl.add("/oauth/token");
        shouldSkipUrl.add("/oauth/check_token");
        shouldSkipUrl.add("/user/getCurrentUser");
    }

    private boolean shouldSkip(String reqPath) {
        for(String skipPath:shouldSkipUrl) {
            if(reqPath.contains(skipPath)) {
                return true;
            }
        }
        return false;
    }

    private TokenInfo getTokenInfo(String authHeader) {
        // 获取token的值
        String token = StringUtils.substringAfter(authHeader, "bearer ");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        // todo 填写你的clientId clientSecret
        headers.setBasicAuth("clientId", "clientSecret");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("token", token);

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);

        // todo 填写你的获取token的地址
        ResponseEntity<TokenInfo> response = restTemplate.exchange("tokenUrl", HttpMethod.POST, entity, TokenInfo.class);

        return response.getBody();
    }
}
