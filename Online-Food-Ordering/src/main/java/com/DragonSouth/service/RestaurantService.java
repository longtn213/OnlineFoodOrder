package com.DragonSouth.service;

import com.DragonSouth.dto.RestaurantDto;
import com.DragonSouth.model.Restaurant;
import com.DragonSouth.model.User;
import com.DragonSouth.request.CreateRestaurantRequest;

import java.util.List;

public interface RestaurantService {

    public Restaurant createRestaurant(CreateRestaurantRequest req, User user);

    public Restaurant updateRestaurant(CreateRestaurantRequest req, Long RestaurantId);

    public void deleteRestaurant(Long RestaurantId);

    public List<Restaurant> getAllRestaurants();

    public List<Restaurant> searchRestaurants(String keyword);

    public Restaurant findRestaurantById(Long RestaurantId);

    public Restaurant getRestaurantByUserId(Long userId);

    public RestaurantDto addToFavorites(Long restaurantId, User user);

    public Restaurant updateRestaurantStatus(Long restaurantId);
}
