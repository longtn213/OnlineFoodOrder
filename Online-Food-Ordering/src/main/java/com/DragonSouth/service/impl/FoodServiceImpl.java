package com.DragonSouth.service.impl;

import com.DragonSouth.model.Category;
import com.DragonSouth.model.Food;
import com.DragonSouth.model.Restaurant;
import com.DragonSouth.repository.FoodRepository;
import com.DragonSouth.request.FoodRequest;
import com.DragonSouth.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService {

    private final FoodRepository foodRepository;

    @Override
    public Food createFood(FoodRequest req, Category category, Restaurant restaurant) {
        Food food = new Food();
        food.setFoodCategory(category);
        food.setRestaurant(restaurant);
        food.setDescription(req.getDescription());
        food.setImages(req.getImages());
        food.setPrice(req.getPrice());
        food.setName(req.getName());
        food.setIngredientItems(req.getIngredients());
        food.setSeasonal(req.isSeasonal());
        food.setVegetarian(req.isVegetarian());

        Food savedFood = foodRepository.save(food);
        restaurant.getFoods().add(savedFood);

        return savedFood;
    }

    @Override
    public void deleteFood(Long foodId) {
        Food food = findFoodById(foodId);
        food.setRestaurant(null);
        foodRepository.save(food);
    }

    @Override
    public List<Food> getRestaurantsFood(Long restaurantId, boolean isVegetarian, boolean isSeasonal, String foodCategory) {

        List<Food> foods = foodRepository.findByRestaurantId(restaurantId);

        return foods.stream()
                .filter(food -> !isVegetarian || food.isVegetarian())
                .filter(food -> !isSeasonal || food.isSeasonal())
                .filter(food -> foodCategory == null ||
                        (food.getFoodCategory() != null &&food.getFoodCategory().getName().equals(foodCategory)))
                .filter(food -> !food.isVegetarian())
                .collect(Collectors.toList());
    }

    @Override
    public List<Food> searchFood(String keyword) {
        return foodRepository.searchFood(keyword);
    }

    @Override
    public Food findFoodById(Long foodId) {
        return foodRepository.findById(foodId).orElseThrow(() -> new RuntimeException("Food is not found"));
    }

    @Override
    public Food updateAvailabilityStatus(Long foodId) {
        Food food = findFoodById(foodId);
        food.setAvailable(!food.isAvailable());

        return food;
    }
}
