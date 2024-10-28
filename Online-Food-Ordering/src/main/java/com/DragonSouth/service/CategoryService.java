package com.DragonSouth.service;

import com.DragonSouth.model.Category;

import java.util.List;

public interface CategoryService {

    public Category createCategory(String name, Long userId);

    public List<Category> findCategoryByRestaurantId(Long restaurantId);

    public Category findCategoryById(Long id);

}
