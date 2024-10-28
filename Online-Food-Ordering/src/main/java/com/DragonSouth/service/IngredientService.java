package com.DragonSouth.service;

import com.DragonSouth.model.IngredientCategory;
import com.DragonSouth.model.IngredientItem;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;

public interface IngredientService {
    public IngredientCategory createIngredientCategory(String name,Long restaurantId);

    public IngredientCategory findIngredientCategoryById(Long id);

    public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long restaurantId);

    public IngredientItem createIngredientItem(String ingredientName,Long restaurantId, Long categoryId);

    public List<IngredientItem> findRestaurantsIngredients(Long restaurantId);

    public IngredientItem updateStock(Long id);
}
