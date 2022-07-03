package com.tyc.order.controller;


import com.tyc.order.service.OrderService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("order")
    @GlobalTransactional
    public String add(Long productId,Integer nums){
        orderService.add(productId, nums);
        return "success";
    }
}
