package com.tyc.sentinel.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("clean/{id}")
    public String url(@PathVariable String id){
        return "hello " + id;
    }

    @GetMapping("test")
    public String test() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(210);
        return "test";
    }
}
