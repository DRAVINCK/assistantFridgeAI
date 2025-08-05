package com.example.assistantFridgeAI.Controller;

import com.example.assistantFridgeAI.Service.FoodItemService;
import com.example.assistantFridgeAI.model.FoodItem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.filter.RequestContextFilter;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/food")
public class FoodItemController {

    private final RequestContextFilter requestContextFilter;
    private FoodItemService service;

    public FoodItemController(FoodItemService foodItemService, RequestContextFilter requestContextFilter) {
        this.service = foodItemService;
        this.requestContextFilter = requestContextFilter;
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
        return ResponseEntity.ok(service.listAll());
    }

    //GET BY ID
    @GetMapping("/list/{id}")
    public ResponseEntity<FoodItem> listById(@PathVariable UUID id){
        return ResponseEntity.ok(service.ListById(id));
    }

    // PUT
    @PutMapping("/updateAll/{id}")
    public ResponseEntity<?> updateWithPut(@PathVariable UUID id, @RequestBody FoodItem food){
        FoodItem foodItemAtualized = service.updateWithPut(id, food);
        if(foodItemAtualized != null){
            return ResponseEntity.ok(foodItemAtualized);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Not found food");

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
        FoodItem foodDelete = service.ListById(id);
        if (foodDelete != null){
            service.delete(id);
            return ResponseEntity.ok("Item: " + foodDelete.getName() + " was successfully deleted!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Item not found.");
    }



}
