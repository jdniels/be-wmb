package com.enigma.service;

import com.enigma.entity.FoodEntities;
import com.enigma.repositories.FoodRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
}
