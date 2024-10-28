package com.DragonSouth.service.impl;

import com.DragonSouth.model.IngredientCategory;
import com.DragonSouth.model.IngredientItem;
import com.DragonSouth.model.Restaurant;
import com.DragonSouth.repository.IngredientCategoryRepository;
import com.DragonSouth.repository.IngredientItemRepository;
import com.DragonSouth.service.IngredientService;
import com.DragonSouth.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class IngredientServiceImpl implements IngredientService {

    private final IngredientItemRepository ingredientItemRepository;
    private final IngredientCategoryRepository ingredientCategoryRepository;
    private final RestaurantService restaurantService;

    @Override
    public IngredientCategory createIngredientCategory(String name, Long restaurantId) {
        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);

        IngredientCategory category = new IngredientCategory();
        category.setName(name);
        category.setRestaurant(restaurant);

        return ingredientCategoryRepository.save(category);
    }

    @Override
    public IngredientCategory findIngredientCategoryById(Long id) {

        return ingredientCategoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Ingredient category not found"));
    }

    @Override
    public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long restaurantId) {
        restaurantService.findRestaurantById(restaurantId);
        return ingredientCategoryRepository.findByRestaurantId(restaurantId);
    }

    @Override
    public IngredientItem createIngredientItem(String ingredientName, Long restaurantId, Long categoryId) {
        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
        IngredientCategory category = findIngredientCategoryById(categoryId);

        IngredientItem ingredientItem = new IngredientItem();
        ingredientItem.setRestaurant(restaurant);
        ingredientItem.setName(ingredientName);
        ingredientItem.setCategory(category);

        IngredientItem item = ingredientItemRepository.save(ingredientItem);
        category.getIngredients().add(item);


        return item;
    }

    @Override
    public List<IngredientItem> findRestaurantsIngredients(Long restaurantId) {
        return ingredientItemRepository.findByRestaurantId(restaurantId);
    }

    @Override
    public IngredientItem updateStock(Long id) {
        IngredientItem ingredientItem = ingredientItemRepository.findById(id).orElseThrow(() -> new RuntimeException("Ingredient not found"));
        ingredientItem.setInStoke(!ingredientItem.isInStoke());

        return ingredientItemRepository.save(ingredientItem);
    }
}
