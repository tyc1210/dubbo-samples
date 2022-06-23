package com.tyc.consumer2.controller;

import com.tyc.api.IHelloService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类描述
 *
 * @author tyc
 * @version 1.0
 * @date 2022-06-21 14:11:33
 */
@RestController
public class HelloController {


    /**
     * dubbo2.7.8版本改为 DubboReference
     * mock: 服务端未启动或超时返回MockHelloService中的数据
     */
    @DubboReference(mock = "com.tyc.consumer2.service.impl.MockHelloService")
    private IHelloService helloService;

    @GetMapping("hello")
    public String hello(){
        return helloService.sayHello("world");
    }
}
