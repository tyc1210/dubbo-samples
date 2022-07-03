package com.tyc.order.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "springcloud-seata-stock")
public interface StockService {

    @PutMapping("/stock/{id}/{num}")
    void reduce(@PathVariable("id") Long id,@PathVariable("num") Integer num);
}
