package com.tyc.consumer.controller;

import com.tyc.api.IHelloService;
import org.apache.dubbo.config.annotation.Reference;
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

    // 直连模式传入url
//    @Reference(url = "dubbo://localhost:20881")
    @Reference()
    private IHelloService helloService;

    @GetMapping("hello")
    public String hello(){
        return helloService.sayHello("world");
    }
}
