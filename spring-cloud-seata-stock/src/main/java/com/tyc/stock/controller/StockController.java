package com.tyc.stock.controller;

import com.tyc.stock.service.StockService;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class StockController {

    @Autowired
    private StockService stockService;

    @PutMapping("/stock/{id}/{num}")
    public void reduce(@PathVariable("id") Long id,@PathVariable("num") Integer num){
        stockService.reduce(id, num);
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
}
