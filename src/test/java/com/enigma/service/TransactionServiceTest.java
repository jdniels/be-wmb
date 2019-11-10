package com.enigma.service;

import com.enigma.entity.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionServiceTest {
    @Autowired
    OrderListService orderListService;
    @Autowired
    TransactionService transactionService;
//
//    @Test
//    public void should_return_data_when_a_transaction_occurs(){
//        Transaction transaction = new Transaction(new BigDecimal(10000), new BigDecimal(5000), new BigDecimal(20000), "Paid", )
//    }

}