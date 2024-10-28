package com.DragonSouth.controller.admin;

import com.DragonSouth.model.Food;
import com.DragonSouth.model.Restaurant;
import com.DragonSouth.request.FoodRequest;
import com.DragonSouth.response.MessageResponse;
import com.DragonSouth.service.FoodService;
import com.DragonSouth.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/food/admin")
@RequiredArgsConstructor
public class AdminFoodController {

    private final FoodService foodService;
    private final RestaurantService restaurantService;

    @PostMapping("/create")
    public ResponseEntity<Food> createFood(@RequestBody FoodRequest foodRequest) {
        Restaurant restaurant = restaurantService.findRestaurantById(foodRequest.getRestaurantId());
        Food food = foodService.createFood(foodRequest, foodRequest.getCategory(),restaurant);

        return new ResponseEntity<>(food, HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Food> updateFoodAvailabilityStatus(@PathVariable Long id) {
        Food food = foodService.updateAvailabilityStatus(id);

        return new ResponseEntity<>(food, HttpStatus.CREATED);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<MessageResponse> deleteFood(@PathVariable Long id) {
        foodService.deleteFood(id);
        MessageResponse res = new MessageResponse();
        res.setMessage("Food deleted successfully");

        return new ResponseEntity<>(res, HttpStatus.CREATED);

    }

}
