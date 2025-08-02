package com.example.assistantFridgeAI.Service;

import com.example.assistantFridgeAI.Repository.FoodItemRepository;
import com.example.assistantFridgeAI.model.FoodItem;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FoodItemService {

    private FoodItemRepository repository;

    public FoodItemService(FoodItemRepository repository) {
        this.repository = repository;
    }

    public FoodItem create(FoodItem foodItem){
        return repository.save(foodItem);
    }

    public List<FoodItem> listAll(){
        return repository.findAll();
    }

    public FoodItem ListById(UUID Id){
        Optional<FoodItem> foodItemOptional =repository.findById(Id);
        return foodItemOptional.orElse(null);
    }

    public Optional<FoodItem> updateWithPatch(UUID id, FoodItem food){
        Optional<FoodItem> foodItemOptional = repository.findById(id);
        if(foodItemOptional.isEmpty()){
            return Optional.empty();
        }

        FoodItem foodItemExists = foodItemOptional.get();

        if (food.getName() != null){
            foodItemExists.setName(food.getName());
        }
        if (food.getCategory() != null){
            foodItemExists.setCategory(food.getCategory());
        }
        if (food.getExpirationDate() != null){
            foodItemExists.setExpirationDate(food.getExpirationDate());
        }
        if (food.getQuantity() != null){
            foodItemExists.setQuantity(food.getQuantity());
        }

        FoodItem foodItemAtualized = repository.save(foodItemExists);

        return Optional.of(foodItemAtualized);
    }

    public void delete(UUID id){
        repository.deleteById(id);
    }





}
