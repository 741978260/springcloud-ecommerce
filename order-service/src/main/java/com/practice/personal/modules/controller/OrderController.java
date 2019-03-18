package com.practice.personal.modules.controller;

import com.practice.personal.modules.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/order/{goodId}")
    public String miaosha(@PathVariable("goodId") String goodId){
        return orderService.miaosha(goodId);
    }

}
