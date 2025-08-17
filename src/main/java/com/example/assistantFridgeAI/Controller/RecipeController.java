package com.example.assistantFridgeAI.Controller;

import com.example.assistantFridgeAI.Service.AIService;
import com.example.assistantFridgeAI.Service.FoodItemService;
import com.example.assistantFridgeAI.model.FoodItem;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class RecipeController {

    private FoodItemService foodItemService;
    private AIService chatGptService;

    public RecipeController(FoodItemService foodItemService, AIService chatGptService) {
        this.foodItemService = foodItemService;
        this.chatGptService = chatGptService;
    }

    @GetMapping("/generate")
    public Mono<ResponseEntity<String>> generateRecipe(){
        List<FoodItem> foodItems = foodItemService.listAll();
        return chatGptService.generateRecipe(foodItems)
                 .map(recipe -> ResponseEntity.ok(recipe))
                 .defaultIfEmpty(ResponseEntity.noContent().build());
    }
}
