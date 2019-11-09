package com.enigma.controller;

import com.enigma.entity.FoodEntities;
import com.enigma.service.FoodService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@CrossOrigin
public class FoodController {

    @Autowired
    FoodService foodService;
    @Autowired
    ObjectMapper mapper;

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
    @PostMapping("/saveFood")
    public FoodEntities saveFoodContainImages(@RequestPart MultipartFile file, @RequestPart String foodFormData) throws Exception {
    FoodEntities newFoodData= foodService.saveFood(mapper.readValue(foodFormData,FoodEntities.class));
    try {
        byte[]dataByte=file.getBytes();
        Path path= Paths.get("/var/www/html/foodImages/"+newFoodData.getIdFood()+".jpg");
        Files.write(path,dataByte);
    } catch (IOException e) {
        e.printStackTrace();
    }
    return newFoodData;
    }

}
