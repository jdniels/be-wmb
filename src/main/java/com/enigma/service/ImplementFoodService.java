package com.enigma.service;

import com.enigma.entity.FoodEntities;
import com.enigma.repositories.FoodRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImplementFoodService implements FoodService {
    @Autowired
    FoodRepositories foodRepositories;
    @Override
    public FoodEntities saveFood(FoodEntities newFood) {
        return foodRepositories.save(newFood);
    }
}
