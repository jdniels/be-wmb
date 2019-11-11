package com.enigma.controller;

import com.enigma.entity.Transaction;
import com.enigma.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class TransactionController {
    @Autowired
    TransactionService transactionService;
    @GetMapping("/transaction")
    public List<Transaction> getTransactions(){
        return transactionService.getTransactions();
    }
    @GetMapping("/transaction/{idTransaction}")
    public Transaction getTransactionById(@PathVariable String idTransaction){
        return transactionService.getTransactionById(idTransaction);
    }
}
