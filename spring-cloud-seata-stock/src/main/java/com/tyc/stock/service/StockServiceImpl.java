package com.tyc.stock.service;

import com.tyc.stock.mapper.StockMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockServiceImpl implements StockService{
    @Autowired
    private StockMapper stockMapper;

    @Override
    public void reduce(Long id, Integer num) {
        stockMapper.update(id, num);
    }
}
