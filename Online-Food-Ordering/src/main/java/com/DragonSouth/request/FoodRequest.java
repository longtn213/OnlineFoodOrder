package com.DragonSouth.request;

import com.DragonSouth.model.Category;
import com.DragonSouth.model.IngredientItem;
import lombok.Data;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class FoodRequest {
    private String name;
    private String description;
    private Long price;
    private Category category;
    private List<String> images;
    @NotNull(message = "Field 'restaurantId' is required")
    private Long restaurantId;
    private boolean vegetarian;
    private boolean seasonal;
    private List<IngredientItem> ingredients;
}
