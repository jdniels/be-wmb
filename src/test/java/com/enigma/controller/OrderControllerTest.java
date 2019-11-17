package com.enigma.controller;

import com.enigma.entity.FoodEntities;
import com.enigma.entity.OrderDetail;
import com.enigma.entity.OrderList;
import com.enigma.entity.TableEntities;
import com.enigma.repositories.FoodRepositories;
import com.enigma.repositories.OrderListRepositories;
import com.enigma.repositories.TableRepositories;
import com.enigma.repositories.TransactionRepositories;
import com.enigma.service.FoodService;
import com.enigma.service.OrderListService;
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
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {
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
    public void saveOrder() throws Exception {
        FoodEntities newFood = new FoodEntities("pecel lele", "FOOD", 10000, 20);
        newFood = foodRepositories.save(newFood);
        TableEntities newTable = new TableEntities(1, "AVAILABLE", 4);
        newTable = tableRepositories.save(newTable);
        List<OrderDetail> orders = new ArrayList<>();
        orders.add(new OrderDetail(2, newFood.getIdFood()));
        OrderList orderList = new OrderList();
        orderList.setIdTable(newTable.getIdTable());
        orderList.setManyCustomers(1);
        orderList.setOrderDetails(orders);
        orderList.setPicCustomer("drika");
        String response = mockMvc.perform(post("/order")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"picCustomer\":\"" + orderList.getPicCustomer() + "\",\"manyCustomers\":" + orderList.getManyCustomers() + "," +
                        "\"orderDetails\":[{\"quantity\":\"" + orderList.getOrderDetails().get(0).getQuantity() + "\",\"foodId\":\"" +
                        "" + orderList.getOrderDetails().get(0).getFoodId() + "\"}],\"idTable\":\"" + orderList.getIdTable() + "\"}"))
                .andReturn().getResponse().getContentAsString();
        OrderList expect = new ObjectMapper().readValue(response, OrderList.class);
        Assert.assertEquals(expect, orderListRepositories.findById(expect.getIdOrder()).get());


    }

    @Test
    public void getAllOrder()throws Exception {
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
        List<OrderList>orderLists=new ArrayList<>();
        orderLists.add(newOrderList);
        String response =mockMvc.perform(get("/orders")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        List<OrderList>orders=new ObjectMapper().readValue(response, new TypeReference<List<OrderList>>() {});
        Assert.assertEquals(orderLists.size(),orders.size());
    }

    @Test
    public void getOrderById()throws Exception {
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
        String response =mockMvc.perform(get("/order/{idOrder}",newOrderList.getIdOrder())).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        OrderList expected =new ObjectMapper().readValue(response, OrderList.class);
        Assert.assertEquals(expected,orderListRepositories.findById(newOrderList.getIdOrder()).get());
    }
}