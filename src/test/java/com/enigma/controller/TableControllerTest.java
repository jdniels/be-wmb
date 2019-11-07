package com.enigma.controller;

import com.enigma.entity.TableEntities;
import com.enigma.repositories.TableRepositories;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TableControllerTest {
    @Autowired
    TableRepositories tableRepositories;
    @Autowired
    MockMvc mockMvc;

    @Before
    public void setUp()  {
        tableRepositories.deleteAll();
    }

    @Test
    public void saveTable_should_return_status_Ok() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        TableEntities newTable = new TableEntities(1,"Available",8);
        mockMvc.perform(post("/table")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(newTable))).andExpect(status().isOk());
    }
    @Test
    public void saveTable_should_exist_in_Database() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        TableEntities newTable = new TableEntities(1,"Available",8);
        String response = mockMvc.perform(post("/table")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(newTable))).andReturn().getResponse().getContentAsString();
        newTable = new ObjectMapper().readValue(response,TableEntities.class);
        Assert.assertEquals(newTable,tableRepositories.findById(newTable.getIdTable()).get());
    }
    @Test
    public void should_return_StatusOk_whenGetAllData() throws Exception{
        mockMvc.perform(get("/table")).andExpect(status().isOk());
    }
}