package com.enigma.controller;

import com.enigma.entity.OrderList;
import com.enigma.service.OrderListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class OrderController {

    @Autowired
    OrderListService orderListService;

    @PostMapping("/order")
    public OrderList saveOrder(@RequestBody OrderList orderForm){
        return orderListService.saveOrder(orderForm);
    }

    @GetMapping("/order")
    public List<OrderList> getAllOrder(){
        return orderListService.getAllOrderList();
    }
    @GetMapping("/order/{idOrder}")
    public OrderList getOrderById(@PathVariable String idOrder){
        return orderListService.getOrderListById(idOrder);
    }
    @GetMapping("/getOrder")
    public Page<OrderList> getOrderByPagination(@RequestParam Integer page, @RequestParam Integer size){
        Pageable pageable = PageRequest.of(page, size);
        return orderListService.getOrderListPagination(pageable);
    }
}
