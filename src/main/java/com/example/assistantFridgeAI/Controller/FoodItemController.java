package com.example.assistantFridgeAI.Controller;

import com.example.assistantFridgeAI.Service.FoodItemService;
import com.example.assistantFridgeAI.model.FoodItem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/food")
public class FoodItemController {

    private FoodItemService service;

    public FoodItemController(FoodItemService foodItemService) {
        this.service = foodItemService;
    }

    // POST
    @PostMapping("/create")
    public ResponseEntity<FoodItem> create(@RequestBody FoodItem foodItem){
        FoodItem foodCreate = service.create(foodItem);
        return ResponseEntity.ok(foodCreate);
    }

    // GET
    @GetMapping("/list")
    public ResponseEntity<List<FoodItem>> listAll(){
        List<FoodItem> foodItems = service.listAll();
        return ResponseEntity.ok(foodItems);
    }

    //GET BY ID
    @GetMapping("/list/{id}")
    public ResponseEntity<FoodItem> listById(@PathVariable UUID id){
        Optional<FoodItem> FoodOp = service.ListById(id);
        if (FoodOp.isPresent()) {
            return ResponseEntity.ok(FoodOp.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(null);

    }

    // PUT
    @PutMapping("/updateAll/{id}")
    public ResponseEntity<FoodItem> updateWithPut(@PathVariable UUID id, @RequestBody FoodItem food){
        return service.ListById(id)
                .map(foodItem -> {
                    FoodItem foodAtualized = service.updateWithPut(id, food);
                    return ResponseEntity.ok(foodAtualized);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //PATCH
    @PatchMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody FoodItem food){
        FoodItem foodAtualized = service.updateWithPatch(id, food);
        if (foodAtualized != null){
            return ResponseEntity.ok(foodAtualized);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("food not found to update");
    }

    // DELETE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable UUID id){
        Optional<FoodItem> foodDelete = service.ListById(id);
        if (foodDelete.isPresent()){
            service.delete(id);
            return ResponseEntity.noContent()
                    .header("Message", "Food item deleted successfully.")
                    .build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Item not found.");
    }



}
