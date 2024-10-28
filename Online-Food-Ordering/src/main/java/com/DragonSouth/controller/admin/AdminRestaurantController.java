package com.DragonSouth.controller.admin;

import com.DragonSouth.model.Restaurant;
import com.DragonSouth.model.User;
import com.DragonSouth.request.CreateRestaurantRequest;
import com.DragonSouth.response.MessageResponse;
import com.DragonSouth.service.RestaurantService;
import com.DragonSouth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/restaurant/admin")
@RequiredArgsConstructor
public class AdminRestaurantController {

    private final RestaurantService restaurantService;

    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody CreateRestaurantRequest req,
                                                       @RequestHeader("Authorization") String jwt) {
        User user = userService.findUserByJwtToken(jwt);

        Restaurant restaurant = restaurantService.createRestaurant(req, user);

        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
    }

    @PutMapping("/update/{restaurantId}")
    public ResponseEntity<Restaurant> updateRestaurant(@RequestBody CreateRestaurantRequest req,
                                                       @PathVariable Long restaurantId) {

        Restaurant restaurant = restaurantService.updateRestaurant(req, restaurantId);

        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }


    @DeleteMapping("/delete/{restaurantId}")
    public ResponseEntity<MessageResponse> deleteRestaurant(@PathVariable Long restaurantId) {
        restaurantService.deleteRestaurant(restaurantId);
        MessageResponse response = new MessageResponse();
        response.setMessage("Restaurant deleted Successfully");


        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/update/{restaurantId}/status")
    public ResponseEntity<Restaurant> updateRestaurantStatus(@PathVariable Long restaurantId) {

        Restaurant restaurant = restaurantService.updateRestaurantStatus(restaurantId);

        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<Restaurant> findRestaurantByUserId(@RequestHeader("Authorization") String jwt) {
        User user = userService.findUserByJwtToken(jwt);
        Restaurant restaurant = restaurantService.getRestaurantByUserId(user.getId());

        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

}
