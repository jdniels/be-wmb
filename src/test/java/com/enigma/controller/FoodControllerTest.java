package com.enigma.controller;

import com.enigma.entity.FoodEntities;
import com.enigma.repositories.FoodRepositories;
import com.enigma.repositories.OrderListRepositories;
import com.enigma.repositories.TableRepositories;
import com.enigma.repositories.TransactionRepositories;
import com.enigma.service.FoodService;
import com.enigma.service.OrderListService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class    FoodControllerTest {
    @Autowired
    FoodRepositories foodRepositories;
    @Autowired
    TableRepositories tableRepositories;
    @Autowired
    TransactionRepositories transactionRepositories;
    @Autowired
    OrderListRepositories orderListRepositories;
    @Autowired
    FoodService foodService;
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
    public void getAllFood() throws Exception{
        FoodEntities food1 = new FoodEntities("Kangkung", "Food", 20000, 10);
        food1 = foodRepositories.save(food1);
        FoodEntities food2 = new FoodEntities("Kangkung", "Food", 20000, 10);
        food2 = foodRepositories.save(food2);
        List<FoodEntities>foodEntities=new ArrayList<>();
        foodEntities.add(food1);
        foodEntities.add(food2);
        String response =mockMvc.perform(get("/foods")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        String expected =mapper.writeValueAsString(foodEntities);
        Assert.assertEquals(expected,response);
    }

    @Test
    public void sould_return_foodById_getFoodById() throws Exception {
        FoodEntities food1 = new FoodEntities("Kangkung", "Food", 20000, 10);
        food1 = foodRepositories.save(food1);
        String response =mockMvc.perform(get("/food/{idFood}",food1.getIdFood())).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        Assert.assertEquals(mapper.writeValueAsString(food1),response);
    }

    @Test
    public void should_size_0_when_deleteFoodBy_id() throws Exception {
        FoodEntities food1 = new FoodEntities("Kangkung", "Food", 20000, 10);
        food1 = foodRepositories.save(food1);
        mockMvc.perform(delete("/food/{idFood}",food1.getIdFood())).andExpect(status().isOk());
        Assert.assertEquals(0,foodRepositories.findAll().size());
    }

    @Test
    public void should_Update_In_Database_When_Update_food() throws Exception {
        FoodEntities food1 = new FoodEntities("Kangkung", "Food", 20000, 10);
        FoodEntities food2 = new FoodEntities("Kulupan", "Food", 20000, 10);
        food1 = foodRepositories.save(food1);
        food2.setIdFood(food1.getIdFood());
        String response = mockMvc.perform(put("/food")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(food2))).andReturn().getResponse().getContentAsString();
        FoodEntities expect = new ObjectMapper().readValue(response, FoodEntities.class);
        Assert.assertEquals(expect, food2);
    }

}