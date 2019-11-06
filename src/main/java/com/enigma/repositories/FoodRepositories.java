package com.enigma.repositories;

import com.enigma.entity.FoodEntities;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepositories extends JpaRepository<FoodEntities,String> {
}
