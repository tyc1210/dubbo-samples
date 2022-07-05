package com.tyc.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类描述
 *
 * @author tyc
 * @version 1.0
 * @date 2022-07-05 15:30:22
 */
@RestController
public class TestController {

    @GetMapping("/say")
    public String say(){
        return "hello";
    }
}
