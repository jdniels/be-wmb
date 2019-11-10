package com.enigma.service;

import com.enigma.entity.Transaction;

import java.util.ArrayList;
import java.util.List;

public interface TransactionService {
    Transaction saveTransaction(Transaction newTransaction);

    List<Transaction> getTransactions();
    Transaction getTransactionById(String idTransaction);
    void deleteTransaction(String idTransaction);

}