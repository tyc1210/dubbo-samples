package com.tyc.order.controller;


import com.tyc.order.feign.StockService;
import com.tyc.order.service.OrderService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private StockService stockService;

    @PostMapping("order")
    @GlobalTransactional
    public String add(Long productId,Integer num){
        orderService.add(productId, num);
        return "success";
    }

    @GetMapping("hello")
    public String hello(){
        return stockService.hello();
    }
}
