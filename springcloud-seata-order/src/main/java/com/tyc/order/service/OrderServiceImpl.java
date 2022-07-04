package com.tyc.order.service;

import com.tyc.order.feign.StockService;
import com.tyc.order.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private StockService stockService;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public void add(Long productId, Integer num) {
        stockService.reduce(productId, num);
        orderMapper.add(productId,num,"success");
        System.out.println("下单");
    }
}
