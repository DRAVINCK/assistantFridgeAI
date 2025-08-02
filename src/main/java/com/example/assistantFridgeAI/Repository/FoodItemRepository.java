package com.example.assistantFridgeAI.Repository;

import com.example.assistantFridgeAI.model.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FoodItemRepository extends JpaRepository<FoodItem, UUID> {


}
