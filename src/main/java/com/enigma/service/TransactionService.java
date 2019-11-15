package com.enigma.service;

import com.enigma.entity.OrderList;
import com.enigma.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

public interface TransactionService {
    Transaction saveTransaction(OrderList newOrderList);

    List<Transaction> getTransactions();
    Transaction getTransactionById(String idTransaction);
    Transaction updatePaymentStatus(Transaction transaction);
    Page<Transaction> getTransactionByPage(Pageable pageable);
    Transaction getTransactionByTable(String tableId);
}
