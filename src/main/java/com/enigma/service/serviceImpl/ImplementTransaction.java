package com.enigma.service.serviceImpl;

import com.enigma.entity.OrderList;
import com.enigma.entity.Transaction;
import com.enigma.service.TransactionService;
import org.springframework.stereotype.Service;

@Service
public class ImplementTransaction implements TransactionService {
    @Override
    public void saveDataTransaction(OrderList newOrder) {
        Transaction transactionData =new Transaction();
        transactionData.setOrderList(newOrder);
        transactionData.setTotal(newOrder.getTotalPrice());
        transactionData.
    }
}
