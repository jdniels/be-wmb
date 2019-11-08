package com.enigma.service;

import com.enigma.entity.FoodEntities;
import com.enigma.entity.OrderDetail;
import com.enigma.entity.OrderList;
import com.enigma.entity.TableEntities;
import com.enigma.repositories.OrderListRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ImplementOrderListService implements OrderListService {
    @Autowired
    OrderListRepositories orderListRepositories;
    @Autowired
    TableService tableService;
    @Autowired
    FoodService foodService;
    @Override
    public OrderList saveOrder(OrderList newOrder) {
        TableEntities table = tableService.getTableById(newOrder.getIdTable());
        newOrder.setTable(table);
        for (OrderDetail items:newOrder.getOrderDetails()) {
            SumOrderTotalAndSubTotal(newOrder, items);
            items.setOrderId(newOrder);
        }
        return orderListRepositories.save(newOrder);
    }

    private void SumOrderTotalAndSubTotal(OrderList newOrder, OrderDetail items) {
        FoodEntities food= foodService.getFoodById(items.getFoodId());
        items.setFood(food);
        foodService.deductQuantityFood(items.getFood().getIdFood(),items.getQuantity());
        items.setSubTotal(foodService.getFoodPriceById(items.getFood().getIdFood()));
        items.setOrderId(newOrder);
        BigDecimal total=items.getSubTotal();
        newOrder.setTotalPrice(total);
    }

    @Override
    public OrderList getOrderListById(String orderId) {
        return orderListRepositories.findById(orderId).get();
    }

    @Override
    public List<OrderList> getAllOrderList() {
        return orderListRepositories.findAll();
    }

    @Override
    public Page<OrderList> getOrderListPagination(Pageable pageable) {
        return null;
    }
}
