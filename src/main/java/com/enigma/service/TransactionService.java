package com.enigma.service;

import com.enigma.entity.OrderList;
import com.enigma.entity.Transaction;

import java.util.ArrayList;
import java.util.List;

public interface TransactionService {
    Transaction saveTransaction(OrderList newOrderList);

    List<Transaction> getTransactions();
    Transaction getTransactionById(String idTransaction);
    void deleteTransaction(String idTransaction);
    Transaction updatePaymentStatus(Transaction transaction);

}
