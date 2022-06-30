package com.tyc.sentinel.config;

import com.alibaba.csp.sentinel.adapter.servlet.callback.UrlCleaner;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class MyUrlCleaner implements UrlCleaner {
    @Override
    public String clean(String s) {
        if(StringUtils.isEmpty(s)){
            return s;
        }
        if(s.startsWith("/clean/")){
            return "/clean/*";
        }
        return s;
    }
}
