package com.tyc.stock.service;

import org.springframework.stereotype.Service;

public interface StockService {
    /**
     * 减库存
     */
    void reduce(Long id,Integer nums);
}
