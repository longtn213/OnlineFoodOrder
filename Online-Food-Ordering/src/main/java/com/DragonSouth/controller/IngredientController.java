package com.DragonSouth.controller;

import com.DragonSouth.model.IngredientCategory;
import com.DragonSouth.model.IngredientItem;
import com.DragonSouth.request.IngredientCategoryRequest;
import com.DragonSouth.request.IngredientItemRequest;
import com.DragonSouth.service.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ingredient/admin")
@RequiredArgsConstructor
public class IngredientController {

    private final IngredientService ingredientService;

    @PostMapping("/category/create")
    public ResponseEntity<IngredientCategory> createIngredientCategory(@RequestBody IngredientCategoryRequest req) {
        IngredientCategory item = ingredientService.createIngredientCategory(req.getName(), req.getRestaurantId());
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @PostMapping("/item/create")
    public ResponseEntity<IngredientItem> createIngredientItem(@RequestBody IngredientItemRequest req) {
        IngredientItem item = ingredientService.createIngredientItem(req.getName(), req.getRestaurantId(), req.getCategoryId());
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/stoke")
    public ResponseEntity<IngredientItem> updateIngredientStoke(@PathVariable Long id) {
        IngredientItem item = ingredientService.updateStock(id );
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<IngredientItem>> getRestaurantIngredient(@PathVariable Long restaurantId) {
        List<IngredientItem> item = ingredientService.findRestaurantsIngredients(restaurantId);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @GetMapping("/restaurant/{restaurantId}/category")
    public ResponseEntity<List<IngredientCategory>> getRestaurantIngredientCategory(@PathVariable Long restaurantId) {
        List<IngredientCategory> item = ingredientService.findIngredientCategoryByRestaurantId(restaurantId);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

}
