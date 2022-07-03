package com.tyc.stock.controller;

import com.tyc.stock.service.StockService;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockController {

    @Autowired
    private StockService stockService;

    @PutMapping("/stock/{id}/{num}")
    public void reduce(@PathVariable("id") Long id,@PathVariable("num") Integer num){
        stockService.reduce(id, num);
    }
}
