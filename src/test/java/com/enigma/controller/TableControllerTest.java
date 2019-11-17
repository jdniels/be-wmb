package com.enigma.controller;

import com.enigma.entity.TableEntities;
import com.enigma.repositories.FoodRepositories;
import com.enigma.repositories.OrderListRepositories;
import com.enigma.repositories.TableRepositories;
import com.enigma.repositories.TransactionRepositories;
import com.enigma.service.TableService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

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
         tableEntities=tableService.saveTable(tableEntities);
        String response = mockMvc.perform(post("/table")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(tableEntities))).andReturn().getResponse().getContentAsString();
        TableEntities expect = new ObjectMapper().readValue(response, TableEntities.class);
        Assert.assertEquals(expect, tableRepositories.findById(tableEntities.getIdTable()).get());
    }

    @Test
    public void should_return_table_when_getTableById()throws Exception {
        TableEntities tableEntities = new TableEntities(1,"AVAILABLE",4);
        tableEntities=tableService.saveTable(tableEntities);
        String response =mockMvc.perform(get("/table/{idTable}",tableEntities.getIdTable())).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        Assert.assertEquals(mapper.writeValueAsString(tableEntities),response);
    }

    @Test
    public void should_return_data_with_pagination_when_getTableWithPagination()throws Exception {
        TableEntities tableEntities1 = new TableEntities(1,"AVAILABLE",4);
        TableEntities tableEntities2 = new TableEntities(2,"AVAILABLE",4);
        tableEntities1=tableService.saveTable(tableEntities1);
        tableEntities2=tableService.saveTable(tableEntities2);
        List<TableEntities> foodEntities=new ArrayList<>();
        foodEntities.add(tableEntities1);
        foodEntities.add(tableEntities2);
        String response =mockMvc.perform(get("/tables")
                .param("size","2").param("page","0")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        Pageable pageable = PageRequest.of(0,2);
        String actual =mapper.writeValueAsString(tableService.getAllWithPagination(pageable));
        Assert.assertEquals(response,actual);
    }

    @Test
    public void should_data_updated_when_updateTable() throws Exception{
        TableEntities tableEntities = new TableEntities(1,"AVAILABLE",4);
        TableEntities tableEntities2 = new TableEntities(2,"AVAILABLE",8);
        tableEntities=tableService.saveTable(tableEntities);
        tableEntities2.setIdTable(tableEntities.getIdTable());
        String response = mockMvc.perform(put("/table")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(tableEntities2))).andReturn().getResponse().getContentAsString();
        Assert.assertEquals(mapper.writeValueAsString(tableService.getTableById(tableEntities.getIdTable())),response);
    }

    @Test
    public void should_return_0_size_when_deleteTableById() throws Exception {
        TableEntities tableEntities = new TableEntities(1,"AVAILABLE",4);
        tableEntities=tableService.saveTable(tableEntities);
        mockMvc.perform(delete("/table/{idTable}",tableEntities.getIdTable())).andExpect(status().isOk());
        Assert.assertEquals(0,tableRepositories.findAll().size());
    }
}



