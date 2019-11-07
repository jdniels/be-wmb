package com.enigma.controller;

import com.enigma.entity.FoodEntities;
import com.enigma.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class FoodController {

    @Autowired
    FoodService foodService;

    @PostMapping("/food")
    public FoodEntities saveFood(@RequestBody FoodEntities foodEntities){
        return foodService.saveFood(foodEntities);
    }
    @GetMapping("/food")
    public List<FoodEntities> getAllFood(){
        return foodService.getAllFood();
    }
    @GetMapping("/food/{idFood}")
    public FoodEntities getFoodById(@PathVariable String idFood){
        return foodService.getFoodById(idFood);
    }
    @DeleteMapping("/food/{idFood}")
    public void deleteFood(@PathVariable String idFood){
        foodService.deleteFoodById(idFood);
    }

}
