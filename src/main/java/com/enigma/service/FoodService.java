package com.enigma.service;

import com.enigma.entity.FoodEntities;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FoodService {
    FoodEntities saveFood(FoodEntities newFood);
    List<FoodEntities> getAllFood();
    FoodEntities getFoodById(String idFood);
    void deleteFoodById(String idFood);
    Page<FoodEntities> getAllFoodPagination(Pageable pageable);
    void deductQuantityFood(String idFood,Integer quantity);
    Integer getFoodPriceById(String idFood);
    FoodEntities updateFood(FoodEntities newData);
}
