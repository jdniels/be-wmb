package com.enigma.service.serviceImpl;

import com.enigma.entity.FoodEntities;
import com.enigma.entity.OrderDetail;
import com.enigma.entity.OrderList;
import com.enigma.entity.TableEntities;
import com.enigma.exeption.StatusTableException;
import com.enigma.exeption.TableCapacityException;
import com.enigma.repositories.OrderListRepositories;
import com.enigma.service.FoodService;
import com.enigma.service.OrderListService;
import com.enigma.service.TableService;
import com.enigma.service.TransactionService;
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
    @Autowired
    TransactionService transactionService;

    @Override
    public OrderList saveOrder(OrderList newOrder) {
        TableEntities table = tableService.getTableById(newOrder.getIdTable());
        if (newOrder.getManyCustomers() > table.getCapacity()) {
            throw new TableCapacityException();
        } else {
            updateStatusTable(table);
            newOrder.setTable(table);
            for (OrderDetail items : newOrder.getOrderDetails()) {
                SumOrderTotalAndSubTotal(newOrder, items);
                items.setOrderId(newOrder);
            }
        }
        newOrder = orderListRepositories.save(newOrder);
        transactionService.saveTransaction(newOrder);
        return newOrder;
    }

    private void updateStatusTable(TableEntities table) {
        if (table.getStatus().equals("AVAILABLE")) {
            table.setStatus("DINING");
        } else {
            throw new StatusTableException();
        }
    }

    private void SumOrderTotalAndSubTotal(OrderList newOrder, OrderDetail items) {
        FoodEntities food = foodService.getFoodById(items.getFoodId());
        items.setFood(food);
        foodService.deductQuantityFood(items.getFood().getIdFood(), items.getQuantity());
        items.setSubTotal(foodService.getFoodPriceById(items.getFood().getIdFood()));
        items.setOrderId(newOrder);
        BigDecimal total = items.getSubTotal();
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
        return orderListRepositories.findAll(pageable);
    }
}
