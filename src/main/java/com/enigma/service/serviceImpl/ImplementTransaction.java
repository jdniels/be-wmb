package com.enigma.service.serviceImpl;

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
    public Transaction saveTransaction(Transaction newTransaction) {
        return transactionRepositories.save(newTransaction);
    }

    @Override
    public List<Transaction> getTransactions() {
        return transactionRepositories.findAll();
    }

}
