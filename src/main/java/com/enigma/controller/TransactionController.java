package com.enigma.controller;

import com.enigma.entity.Transaction;
import com.enigma.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class TransactionController {
    @Autowired
    TransactionService transactionService;
    @GetMapping("/transactions")
    public List<Transaction> getTransactions(){
        return transactionService.getTransactions();
    }
    @GetMapping("/transaction/{idTransaction}")
    public Transaction getTransactionById(@PathVariable String idTransaction){
        return transactionService.getTransactionById(idTransaction);
    }
    @PostMapping("/transaction")
    public Transaction updatePayment(@RequestBody Transaction transaction){
        return  transactionService.updatePaymentStatus(transaction);
    }
    @GetMapping("/page-transactions")
    public Page<Transaction> getTransactionByPage(@RequestParam Integer size, @RequestParam Integer page){
        Pageable pageable = PageRequest.of(page, size);
        return transactionService.getTransactionByPage(pageable);
    }
    @GetMapping("/transaction-table/{idTable}")
    public Transaction getTransactionByTable(@PathVariable String idTable){
        return transactionService.getTransactionByTable(idTable);
    }
}
