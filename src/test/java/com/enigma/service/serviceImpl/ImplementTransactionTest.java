package com.enigma.service.serviceImpl;

import com.enigma.entity.*;
import com.enigma.repositories.FoodRepositories;
import com.enigma.repositories.OrderListRepositories;
import com.enigma.repositories.TableRepositories;
import com.enigma.repositories.TransactionRepositories;
import com.enigma.service.OrderListService;
import com.enigma.service.TransactionService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ImplementTransactionTest {
    @Autowired
    FoodRepositories foodRepositories;
    @Autowired
    TableRepositories tableRepositories;
    @Autowired
    TransactionRepositories transactionRepositories;
    @Autowired
    OrderListRepositories orderListRepositories;
    @Autowired
    OrderListService orderListService;
    @Autowired
    TransactionService transactionService;

    @Before
    public void setUp() {
        transactionRepositories.deleteAll();
        orderListRepositories.deleteAll();
        foodRepositories.deleteAll();
        tableRepositories.deleteAll();
    }

    @After
    public void tearDown() {
        transactionRepositories.deleteAll();
        orderListRepositories.deleteAll();
        foodRepositories.deleteAll();
        tableRepositories.deleteAll();
    }

    @Test
    public void saveTransaction() {
        TableEntities newTable = new TableEntities(3, "AVAILABLE", 5);
        newTable = tableRepositories.save(newTable);
        FoodEntities newFood1 = new FoodEntities("pecel", "MAKANAN", 10000, 50);
        FoodEntities newFood2 = new FoodEntities("sup", "MAKANAN", 10000, 50);
        newFood1 = foodRepositories.save(newFood1);
        newFood2 = foodRepositories.save(newFood2);
        List<OrderDetail> orderDetails = new ArrayList<>();
        orderDetails.add(new OrderDetail(2, newFood1.getIdFood()));
        orderDetails.add(new OrderDetail(2, newFood2.getIdFood()));
        OrderList newOrderList = new OrderList("RIfqi", 4, orderDetails, newTable.getIdTable());
        newOrderList = orderListService.saveOrder(newOrderList);
        Transaction transaction;
        transaction = transactionService.saveTransaction(newOrderList);
        assertEquals(transaction, transactionService.getTransactionById(transaction.getIdTransaction()));
    }

    @Test
    public void getTransactions() {
        TableEntities newTable = new TableEntities(3, "AVAILABLE", 5);
        newTable = tableRepositories.save(newTable);
        FoodEntities newFood1 = new FoodEntities("pecel", "MAKANAN", 10000, 50);
        FoodEntities newFood2 = new FoodEntities("sup", "MAKANAN", 10000, 50);
        newFood1 = foodRepositories.save(newFood1);
        newFood2 = foodRepositories.save(newFood2);
        List<OrderDetail> orderDetails = new ArrayList<>();
        orderDetails.add(new OrderDetail(2, newFood1.getIdFood()));
        orderDetails.add(new OrderDetail(2, newFood2.getIdFood()));
        OrderList newOrderList = new OrderList("RIfqi", 4, orderDetails, newTable.getIdTable());
        orderListService.saveOrder(newOrderList);
        Integer actual=transactionService.getTransactions().size();
        Integer expectedSize=1;
        assertEquals(expectedSize,actual);
    }

    @Test
    public void getTransactionById() {
        TableEntities newTable = new TableEntities(3, "AVAILABLE", 5);
        newTable = tableRepositories.save(newTable);
        FoodEntities newFood1 = new FoodEntities("pecel", "MAKANAN", 10000, 50);
        FoodEntities newFood2 = new FoodEntities("sup", "MAKANAN", 10000, 50);
        newFood1 = foodRepositories.save(newFood1);
        newFood2 = foodRepositories.save(newFood2);
        List<OrderDetail> orderDetails = new ArrayList<>();
        orderDetails.add(new OrderDetail(2, newFood1.getIdFood()));
        orderDetails.add(new OrderDetail(2, newFood2.getIdFood()));
        OrderList newOrderList = new OrderList("RIfqi", 4, orderDetails, newTable.getIdTable());
        newOrderList = orderListService.saveOrder(newOrderList);
        Transaction transaction;
        transaction = transactionService.saveTransaction(newOrderList);
        assertEquals(transaction,transactionService.getTransactionById(transaction.getIdTransaction()));
    }

    @Test
    public void updatePaymentStatus() {
        TableEntities newTable = new TableEntities(3, "AVAILABLE", 5);
        newTable = tableRepositories.save(newTable);
        FoodEntities newFood1 = new FoodEntities("pecel", "MAKANAN", 10000, 50);
        FoodEntities newFood2 = new FoodEntities("sup", "MAKANAN", 10000, 50);
        newFood1 = foodRepositories.save(newFood1);
        newFood2 = foodRepositories.save(newFood2);
        List<OrderDetail> orderDetails = new ArrayList<>();
        orderDetails.add(new OrderDetail(2, newFood1.getIdFood()));
        orderDetails.add(new OrderDetail(2, newFood2.getIdFood()));
        OrderList newOrderList = new OrderList("RIfqi", 4, orderDetails, newTable.getIdTable());
        newOrderList = orderListService.saveOrder(newOrderList);
        Transaction transaction= transactionService.saveTransaction(newOrderList);
        Transaction transaction1= new Transaction();
        transaction1.setIdTransaction(transaction.getIdTransaction());
        transaction1.setPay(100000);
        transaction1.setOrderList(transaction.getOrderList());
        transaction1.setPaymentDate(transaction.getPaymentDate());
        transaction1.setPaymentStatus(transaction.getPaymentStatus());
        transaction1.setTotal(transaction.getTotal());
        assertEquals(transactionService.updatePaymentStatus(transaction1),transactionRepositories.findById(transaction.getIdTransaction()).get());
    }

    @Test
    public void getTransactionByPage() {
        TableEntities newTable = new TableEntities(3, "AVAILABLE", 5);
        newTable = tableRepositories.save(newTable);
        FoodEntities newFood1 = new FoodEntities("pecel", "MAKANAN", 10000, 50);
        FoodEntities newFood2 = new FoodEntities("sup", "MAKANAN", 10000, 50);
        newFood1 = foodRepositories.save(newFood1);
        newFood2 = foodRepositories.save(newFood2);
        List<OrderDetail> orderDetails = new ArrayList<>();
        orderDetails.add(new OrderDetail(2, newFood1.getIdFood()));
        orderDetails.add(new OrderDetail(2, newFood2.getIdFood()));
        OrderList newOrderList = new OrderList("RIfqi", 4, orderDetails, newTable.getIdTable());
        orderListService.saveOrder(newOrderList);
        Integer actual=transactionService.getTransactions().size();
        Integer expectedSize=1;
        Pageable pageable= PageRequest.of(0,1);
        assertEquals(1, transactionService.getTransactionByPage(pageable).getTotalElements());
    }

    @Test
    public void getTransactionByTable() {
        TableEntities newTable = new TableEntities(3, "AVAILABLE", 5);
        newTable = tableRepositories.save(newTable);
        FoodEntities newFood1 = new FoodEntities("pecel", "MAKANAN", 10000, 50);
        FoodEntities newFood2 = new FoodEntities("sup", "MAKANAN", 10000, 50);
        newFood1 = foodRepositories.save(newFood1);
        newFood2 = foodRepositories.save(newFood2);
        List<OrderDetail> orderDetails = new ArrayList<>();
        orderDetails.add(new OrderDetail(2, newFood1.getIdFood()));
        orderDetails.add(new OrderDetail(2, newFood2.getIdFood()));
        OrderList newOrderList = new OrderList("RIfqi", 4, orderDetails, newTable.getIdTable());
        newOrderList = orderListService.saveOrder(newOrderList);
        Transaction transaction =new Transaction(newOrderList.getTotalPrice(),0,"UNPAID",newOrderList);
        transaction = transactionRepositories.save(transaction);
        Transaction actual=transactionService.getTransactionByTable(newTable.getIdTable());
        assertEquals(transaction.getTotal(), actual.getTotal());
    }
}