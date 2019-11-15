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

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FoodControllerTest {
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
    public void Sould_return_status_OK() throws Exception {
        FoodEntities food = new FoodEntities("Kangkung", "Food", 20000, 10);
        mockMvc.perform(post("/food").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(food))).andExpect(status().isOk());
    }

    @Test
    public void should_Exist_In_Database_When_Save_Artist() throws Exception {
        FoodEntities food = new FoodEntities("Kangkung", "Food", 20000, 10);
        food = foodRepositories.save(food);
        String response = mockMvc.perform(post("/food")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(food))).andReturn().getResponse().getContentAsString();
        FoodEntities expectArtist = new ObjectMapper().readValue(response, FoodEntities.class);
        Assert.assertEquals(expectArtist, foodRepositories.findById(food.getIdFood()).get());
    }

    @Test
    public void getAllFood() {
    }

    @Test
    public void Sould_return_foodById_getFoodById() throws Exception {
    }

    @Test
    public void deleteFood() {
    }

    @Test
    public void saveFoodContainImages() {
    }

    @Test
    public void updateFoodById() {
    }
}