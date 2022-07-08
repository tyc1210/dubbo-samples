package com.tyc.provider4.nacos;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * 类描述
 *
 * @author tyc
 * @version 1.0
 * @date 2022-07-06 16:01:40
 */
@Component
@RefreshScope
public class TestRefreshScope {

    @Value("${info}")
    private String info;

    public String getInfo() {
        return info;
    }
}
