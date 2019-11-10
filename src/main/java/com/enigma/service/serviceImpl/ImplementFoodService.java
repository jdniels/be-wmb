package com.enigma.service.serviceImpl;

import com.enigma.entity.FoodEntities;
import com.enigma.exeption.InsufficientFoodQuantityException;
import com.enigma.repositories.FoodRepositories;
import com.enigma.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ImplementFoodService implements FoodService {
    @Autowired
    FoodRepositories foodRepositories;

    @Override
    public FoodEntities saveFood(FoodEntities newFood) {
        return foodRepositories.save(newFood);
    }

    @Override
    public List<FoodEntities> getAllFood() {
        return foodRepositories.findAll();
    }

    @Override
    public FoodEntities getFoodById(String idFood) {
        return foodRepositories.findById(idFood).get();
    }

    @Override
    public void deleteFoodById(String idFood) {
        foodRepositories.deleteById(idFood);
    }

    @Override
    public Page<FoodEntities> getAllFoodPagination(Pageable pageable) {
        return foodRepositories.findAll(pageable);
    }

    @Override
    public void deductQuantityFood(String idFood, Integer quantity) {
        FoodEntities food = getFoodById(idFood);
        if (food.getQuantity() < quantity) {
                throw  new InsufficientFoodQuantityException();
        } else {
            food.deductQuantityFood(quantity);
            saveFood(food);
        }
    }

    @Override
    public BigDecimal getFoodPriceById(String idFood) {
        return getFoodById(idFood).getPrice();
    }
}
