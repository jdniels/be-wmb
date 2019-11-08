package com.enigma.service;

import com.enigma.entity.FoodEntities;
import com.enigma.entity.TableEntities;
import com.enigma.repositories.FoodRepositories;
import com.enigma.repositories.OrderListRepositories;
import com.enigma.repositories.TableRepositories;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ImplementOrderListServiceTest {
    @Autowired
    OrderListService orderListService;
    @Autowired
    OrderListRepositories orderListRepositories;
    @Autowired
    FoodRepositories foodRepositories;
    @Autowired
    TableRepositories tableRepositories;
    @Before
    public void setUp() throws Exception {
        foodRepositories.deleteAll();
        tableRepositories.deleteAll();
        orderListRepositories.deleteAll();
    }
    @Test
    public void should_exist_in_database_save_order(){
        TableEntities newTable=new TableEntities(3,"AVALIABLE",5);
        newTable=tableRepositories.save(newTable);
        FoodEntities newFood1 = new FoodEntities("pecel","MAKANAN",new BigDecimal(10000),50);
        FoodEntities newFood2 = new FoodEntities("sup","MAKANAN",new BigDecimal(10000),50);
        newFood1=foodRepositories.save(newFood1);
        newFood2=foodRepositories.save(newFood2);

    }
}