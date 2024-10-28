package com.DragonSouth.service.impl;

import com.DragonSouth.model.Cart;
import com.DragonSouth.model.CartItem;
import com.DragonSouth.model.Food;
import com.DragonSouth.model.User;
import com.DragonSouth.repository.CartItemRepository;
import com.DragonSouth.repository.CartRepository;
import com.DragonSouth.request.AddCartItemRequest;
import com.DragonSouth.service.CartService;
import com.DragonSouth.service.FoodService;
import com.DragonSouth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final UserService userService;
    private final FoodService foodService;

    @Override
    public CartItem addItemToCart(AddCartItemRequest req, String jwt) {
        User user = userService.findUserByJwtToken(jwt);

        Food food = foodService.findFoodById(req.getFoodId());
        Cart cart =cartRepository.findCartByCustomerId(user.getId());
        for (CartItem cartItem:cart.getItems()){
            if(cartItem.getFood().equals(food)){
                int newQuantity = cartItem.getQuantity()+req.getQuantity();
                return updateCartItemQuantity(cartItem.getId(), newQuantity);
            }
        }

        CartItem newCartItem = new CartItem();
        newCartItem.setFood(food);
        newCartItem.setQuantity(req.getQuantity());
        newCartItem.setIngredients(req.getIngredients());
        newCartItem.setTotalPrice(req.getQuantity()* food.getPrice());
        CartItem savedCartItem = cartItemRepository.save(newCartItem);

        cart.getItems().add(savedCartItem);

        return savedCartItem;
    }

    @Override
    public CartItem updateCartItemQuantity(Long cartItemId, int quantity) {
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(() -> new RuntimeException("cart item not found"));
        cartItem.setQuantity(quantity);
        cartItem.setTotalPrice(cartItem.getFood().getPrice() * quantity);

        return cartItemRepository.save(cartItem);
    }

    @Override
    public Cart removeItemFromCart(Long cartItemId, String jwt) {
        User user = userService.findUserByJwtToken(jwt);
        Cart cart =cartRepository.findCartByCustomerId(user.getId());
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(() -> new RuntimeException("cart item not found"));

        cart.getItems().remove(cartItem);
        return cartRepository.save(cart);
    }

    @Override
    public Long calculateCartTotals(Cart cart) {
        long total = 0;
        for (CartItem cartItem:cart.getItems()){
            total+= cartItem.getFood().getPrice()*cartItem.getQuantity();
        }

        return total;
    }

    @Override
    public Cart findCartById(Long id) {
        return cartRepository.findById(id).orElseThrow(() -> new RuntimeException("cart not found with id" + id));
    }

    @Override
    public Cart findCartByUserId(String jwt) {
        User user = userService.findUserByJwtToken(jwt);
        return cartRepository.findCartByCustomerId(user.getId());
    }

    @Override
    public Cart clearCart(String jwt) {
        Cart cart = findCartByUserId(jwt);
        cart.getItems().clear();

        return cartRepository.save(cart);
    }
}
