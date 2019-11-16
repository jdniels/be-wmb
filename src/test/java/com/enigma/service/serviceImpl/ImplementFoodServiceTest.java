package com.enigma.service.serviceImpl;

import com.enigma.entity.FoodEntities;
import com.enigma.repositories.FoodRepositories;
import com.enigma.repositories.OrderListRepositories;
import com.enigma.repositories.TableRepositories;
import com.enigma.service.FoodService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ImplementFoodServiceTest {
    @Autowired
    FoodRepositories foodRepositories;
    @Autowired
    OrderListRepositories orderListRepositories;
    @Autowired
    TableRepositories tableRepositories;
    @Autowired
    FoodService foodService;

    @Before
    public void setup() {
        foodRepositories.deleteAll();
    }

    @After
    public void tearDown() {
        foodRepositories.deleteAll();
    }

    @Test
    public void should_exist_in_database_when_saveFood() {
        FoodEntities newFood = new FoodEntities("pecel lele", "MAKANAN", 10000, 20);
        newFood = foodService.saveFood(newFood);
        assertEquals(newFood, foodRepositories.findById(newFood.getIdFood()).get());
    }

    @Test
    public void should_return_all_data_when_getAllFood() {
        FoodEntities foodEntities1 = new FoodEntities("Kangkung", "Food", 20000, 10);
        FoodEntities foodEntities2 = new FoodEntities("Sayur Asem", "Food", 15000, 1);
        foodEntities1 = foodRepositories.save(foodEntities1);
        foodEntities2 = foodRepositories.save(foodEntities2);
        List<FoodEntities> entitiesList = new ArrayList<>();
        entitiesList.add(foodEntities1);
        entitiesList.add(foodEntities2);
        assertEquals(entitiesList, foodService.getAllFood());
    }

    @Test
    public void should_return_same_data_as_id_when_getFoodById() {
        FoodEntities foodEntities1 = new FoodEntities("Kangkung", "Food", 20000, 10);
        foodEntities1 = foodRepositories.save(foodEntities1);
        assertEquals(foodEntities1, foodService.getFoodById(foodEntities1.getIdFood()));
    }

    @Test
    public void should_return_notFound_whenData_delete() {
        FoodEntities foodEntities1 = new FoodEntities("Kangkung", "Food", 20000, 10);
        foodEntities1 = foodRepositories.save(foodEntities1);
        foodService.deleteFoodById(foodEntities1.getIdFood());
    }

    @Test
    public void should_return_data_food_with_pagination() {
        FoodEntities foodEntities1 = new FoodEntities("Kangkung", "Food", 20000, 10);
        FoodEntities foodEntities2 = new FoodEntities("Sayur Asem", "Food", 15000, 10);
        foodRepositories.save(foodEntities1);
        foodRepositories.save(foodEntities2);
        Pageable pageable = PageRequest.of(0, 2);
        assertEquals(2, foodService.getAllFoodPagination(pageable).getTotalElements());
    }

    @Test
    public void deductQuantityFood() {
        FoodEntities foodEntities = new FoodEntities("Sayur Asem", "Food", 15000, 10);
        foodEntities = foodRepositories.save(foodEntities);
        foodService.deductQuantityFood(foodEntities.getIdFood(), 6);
        assertEquals(java.util.Optional.ofNullable(4), java.util.Optional.ofNullable(foodService.getFoodById(foodEntities.getIdFood()).getQuantity()));
    }

    @Test
    public void getFoodPriceById() {
        FoodEntities foodEntities = new FoodEntities("Sayur Asem", "Food", 15000, 10);
        foodEntities = foodRepositories.save(foodEntities);
        Integer expect = 15000;
        assertEquals(expect, foodService.getFoodPriceById(foodEntities.getIdFood()));
    }

    @Test
    public void updateFood() {
        FoodEntities foodEntities1 = new FoodEntities("Kangkung", "Food", 20000, 10);
        FoodEntities foodEntities2 = new FoodEntities("Sayur Asem", "Food", 15000, 10);
        foodEntities1 = foodRepositories.save(foodEntities1);
        foodEntities2.setIdFood(foodEntities1.getIdFood());
        foodService.updateFood(foodEntities2);
        assertEquals(foodRepositories.findById(foodEntities1.getIdFood()).get(), foodEntities2);
    }
}
