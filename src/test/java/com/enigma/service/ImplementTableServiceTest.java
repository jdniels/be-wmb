package com.enigma.service;

import com.enigma.entity.TableEntities;
import com.enigma.repositories.TableRepositories;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ImplementTableServiceTest {
    @Autowired
    TableRepositories tableRepositories;
    @Autowired
    TableService tableService;
    @Before
    public void setup(){
        tableRepositories.deleteAll();
    }
    @Test
    public void should_created_id_database_when_createTable(){
        TableEntities newTable =new TableEntities(1,"Avaliable",4);
        newTable=tableService.saveTable(newTable);
        assertEquals(newTable,tableRepositories.findById(newTable.getIdTable()).get());

    }

}