package com.enigma.repositories;

import com.enigma.entity.FoodEntities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepositories extends JpaRepository<FoodEntities,String> {
}
