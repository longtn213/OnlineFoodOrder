package com.DragonSouth.controller;

import com.DragonSouth.dto.RestaurantDto;
import com.DragonSouth.model.Restaurant;
import com.DragonSouth.model.User;
import com.DragonSouth.request.CreateRestaurantRequest;
import com.DragonSouth.service.RestaurantService;
import com.DragonSouth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurant")
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;

    private final UserService userService;

    @GetMapping("/search")
    public ResponseEntity<List<Restaurant>> searchRestaurant(@RequestParam String keyword) {
        List<Restaurant> restaurants = restaurantService.searchRestaurants(keyword);

        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<Restaurant>> getAllRestaurant() {

        List<Restaurant> restaurants = restaurantService.getAllRestaurants();

        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable Long id) {

        Restaurant restaurants = restaurantService.findRestaurantById(id);

        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }

    @PutMapping("/{id}/add-favorites")
    public ResponseEntity<RestaurantDto> addToFavorites(@PathVariable Long id,
                                                        @RequestHeader("Authorization") String jwt) {
        User user = userService.findUserByJwtToken(jwt);
        RestaurantDto dto = restaurantService.addToFavorites(id,user);

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
}
