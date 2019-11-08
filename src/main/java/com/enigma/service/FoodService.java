package com.enigma.service;

import com.enigma.entity.FoodEntities;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface FoodService {
    FoodEntities saveFood(FoodEntities newFood);
    List<FoodEntities> getAllFood();
    FoodEntities getFoodById(String idFood);
    void deleteFoodById(String idFood);
    Page<FoodEntities> getAllFoodPagination(Pageable pageable);
    void deductQuantityFood(String idFood,Integer quantity);
    BigDecimal getFoodPriceById(String idFood);
}
