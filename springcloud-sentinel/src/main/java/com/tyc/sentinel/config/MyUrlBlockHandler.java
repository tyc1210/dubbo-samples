package com.tyc.sentinel.config;

import com.alibaba.csp.sentinel.adapter.servlet.callback.UrlBlockHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MyUrlBlockHandler implements UrlBlockHandler {
    private static String result;

    static {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",999);
        jsonObject.put("msg","访问人数过多");
        result = jsonObject.toJSONString();
    }

    @Override
    public void blocked(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, BlockException e) throws IOException {
        httpServletResponse.setHeader("Content-Type","application/json;charset=UTF-8");

        httpServletResponse.getWriter().write(result);
    }
}
