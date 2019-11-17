package com.enigma.service;

import com.enigma.entity.OrderList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderListService {
    OrderList saveOrder(OrderList newOrder);

    OrderList getOrderListById(String orderId);

    List<OrderList> getAllOrderList();

    Page<OrderList> getOrderListPagination(Pageable pageable);
}
