package com.enigma.service;

import com.enigma.entity.FoodEntities;
import com.enigma.entity.OrderDetail;
import com.enigma.entity.OrderList;
import com.enigma.entity.TableEntities;
import com.enigma.repositories.OrderListRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            FoodEntities food= foodService.getFoodById(items.getFoodId());
            items.setFood(food);
            foodService.deductQuantityFood(items.getFood().getIdFood(),items.getQuantity());
            items.setSubTotal(foodService.getFoodPriceById(items.getFood().getIdFood()));
            items.setOrderId(newOrder);

        }
        return null;
    }
}
