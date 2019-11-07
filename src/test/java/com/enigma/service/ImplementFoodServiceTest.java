package com.enigma.service;

import com.enigma.entity.FoodEntities;
import com.enigma.repositories.FoodRepositories;
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
public class ImplementFoodServiceTest {
    @Autowired
    FoodRepositories foodRepositories;
    @Autowired
    FoodService foodService;
    @Before
    public void setUp() throws Exception {
    foodRepositories.deleteAll();
    }
    @Test
    public void should_exist_in_database_when_saveFood(){
        FoodEntities newFood=new FoodEntities("pecel lele","MAKANAN",new BigDecimal(10000),20);
        newFood=foodService.saveFood(newFood);
        assertEquals(newFood,foodRepositories.findById(newFood.getIdFood()).get());
    }
}