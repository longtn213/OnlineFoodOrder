package com.DragonSouth.request;

import lombok.Data;

@Data
public class IngredientItemRequest {
    String name;
    private Long categoryId;
    private Long restaurantId;
}
