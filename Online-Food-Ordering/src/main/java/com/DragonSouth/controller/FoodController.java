package com.DragonSouth.controller;

import com.DragonSouth.model.Food;
import com.DragonSouth.request.FoodRequest;
import com.DragonSouth.service.FoodService;
import com.DragonSouth.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food")
@RequiredArgsConstructor
public class FoodController {
    private final FoodService foodService;
    private final RestaurantService restaurantService;

    @GetMapping()
    public ResponseEntity<List<Food>> searchFood(@RequestParam String name) {
        List<Food> food = foodService.searchFood(name);
        return new ResponseEntity<>(food, HttpStatus.CREATED);

    }
    @GetMapping("/restaurant")
    public ResponseEntity<List<Food>> getRestaurantFood(@RequestBody(required = false) @Validated FoodRequest request) {
        List<Food> food = foodService.getRestaurantsFood(request.getRestaurantId(), request.isVegetarian(), request.isSeasonal(),request.getCategory().getName() );
        return new ResponseEntity<>(food, HttpStatus.CREATED);

    }
}
