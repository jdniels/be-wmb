package com.enigma.controller;

import com.enigma.entity.OrderList;
import com.enigma.service.OrderListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class OrderController {

    @Autowired
    OrderListService orderListService;

    @PostMapping("/saveOrder")
    public OrderList saveOrder(@RequestBody OrderList orderForm){
        return orderListService.saveOrder(orderForm);
    }
}
