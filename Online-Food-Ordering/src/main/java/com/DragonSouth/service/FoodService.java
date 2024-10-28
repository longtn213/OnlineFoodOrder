package com.DragonSouth.service;

import com.DragonSouth.model.Category;
import com.DragonSouth.model.Food;
import com.DragonSouth.model.Restaurant;
import com.DragonSouth.request.FoodRequest;

import java.util.List;

public interface FoodService {

    public Food createFood(FoodRequest food, Category category, Restaurant restaurant);

    void deleteFood(Long foodId);

    public List<Food> getRestaurantsFood(Long restaurantId,
                                         boolean isVegetarian,
                                         boolean isSeasonal,
                                         String foodCategory);

    public List<Food> searchFood(String keyword);

    public Food findFoodById(Long foodId);

    public Food updateAvailabilityStatus(Long foodId);
}
