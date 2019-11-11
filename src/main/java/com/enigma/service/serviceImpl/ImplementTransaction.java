package com.enigma.service.serviceImpl;

import com.enigma.entity.OrderList;
import com.enigma.entity.Transaction;
import com.enigma.repositories.TransactionRepositories;
import com.enigma.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImplementTransaction implements TransactionService {
    @Autowired
    TransactionRepositories transactionRepositories;

    @Override
    public Transaction saveTransaction(OrderList newOrderList) {
        Transaction transactionData =new Transaction();
        transactionData.setOrderList(newOrderList);
        transactionData.setTotal(newOrderList.getTotalPrice());
        transactionData.setPaymentStatus("UNPAID");
        transactionData =transactionRepositories.save(transactionData);
        return transactionData;
    }

    @Override
    public List<Transaction> getTransactions() {
        return transactionRepositories.findAll();
    }

    @Override
    public Transaction getTransactionById(String idTransaction) {
        return transactionRepositories.findById(idTransaction).get();
    }

    @Override
    public void deleteTransaction(String idTransaction) {
         transactionRepositories.deleteById(idTransaction);
    }

}
