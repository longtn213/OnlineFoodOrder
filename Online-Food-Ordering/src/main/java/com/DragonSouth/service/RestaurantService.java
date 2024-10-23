package com.DragonSouth.service;

import com.DragonSouth.model.Restaurant;
import com.DragonSouth.model.User;
import com.DragonSouth.request.CreateRestaurantRequest;

public interface RestaurantService {

    public Restaurant createRestaurant(CreateRestaurantRequest req, User user);

    public Restaurant updateRestaurant(CreateRestaurantRequest req, Long RestaurantId);

    public void deleteRestaurant(Long RestaurantId);
}
