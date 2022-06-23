package com.tyc.consumer2.service.impl;

import com.tyc.api.IHelloService;

/**
 * 类描述
 *
 * @author tyc
 * @version 1.0
 * @date 2022-06-23 11:41:35
 */
public class MockHelloService implements IHelloService {
    @Override
    public String sayHello(String name) {
        return "服务降级、暂时无法访问";
    }
}
