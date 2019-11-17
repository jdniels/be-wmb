package com.enigma.controller;

import com.enigma.entity.*;
import com.enigma.repositories.FoodRepositories;
import com.enigma.repositories.OrderListRepositories;
import com.enigma.repositories.TableRepositories;
import com.enigma.repositories.TransactionRepositories;
import com.enigma.service.FoodService;
import com.enigma.service.OrderListService;
import com.enigma.service.TransactionService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TransactionControllerTest {
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
    @Autowired
    MockMvc mockMvc;

    ObjectMapper mapper = new ObjectMapper();

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
    public void getTransactions() throws Exception{
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
        mockMvc.perform(get("/transactions")).andExpect(status().isOk());
    }
}