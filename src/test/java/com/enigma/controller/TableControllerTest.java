package com.enigma.controller;

import com.enigma.entity.TableEntities;
import com.enigma.repositories.FoodRepositories;
import com.enigma.repositories.OrderListRepositories;
import com.enigma.repositories.TableRepositories;
import com.enigma.repositories.TransactionRepositories;
import com.enigma.service.TableService;
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

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TableControllerTest {
    @Autowired
    FoodRepositories foodRepositories;
    @Autowired
    TableRepositories tableRepositories;
    @Autowired
    TransactionRepositories transactionRepositories;
    @Autowired
    OrderListRepositories orderListRepositories;
    @Autowired
    TableService tableService;
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
        TableEntities tableEntities = new TableEntities(1,"AVAILABLE",4);
        mockMvc.perform(post("/table").contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(tableEntities))).andExpect(status().isOk());
    }

    @Test
    public void should_Exist_In_Database_When_Save_Table() throws Exception {
        TableEntities tableEntities = new TableEntities(1,"AVAILABLE",4);
        tableService.saveTable(tableEntities);
        String response = mockMvc.perform(post("/table")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(tableEntities))).andReturn().getResponse().getContentAsString();
        TableEntities expect = new ObjectMapper().readValue(response, TableEntities.class);
        Assert.assertEquals(expect, tableRepositories.findById(tableEntities.getIdTable()).get());
    }
}



