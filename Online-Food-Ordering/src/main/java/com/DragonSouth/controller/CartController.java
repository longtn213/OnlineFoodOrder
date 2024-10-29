package com.DragonSouth.controller;

import com.DragonSouth.model.Cart;
import com.DragonSouth.model.CartItem;
import com.DragonSouth.model.User;
import com.DragonSouth.request.AddCartItemRequest;
import com.DragonSouth.request.UpdateCartItemRequest;
import com.DragonSouth.service.CartService;
import com.DragonSouth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final UserService userService;

    @PutMapping("/cart/add")
    public ResponseEntity<CartItem> addItemToCart(@RequestBody AddCartItemRequest req,
                                                  @RequestHeader("Authorization") String jwt){
        CartItem cartItem =cartService.addItemToCart(req,jwt);
        return ResponseEntity.ok(cartItem);
    }

    @PutMapping("/cart-item/update")
    public ResponseEntity<CartItem> updateCartItemQuantity(@RequestBody UpdateCartItemRequest req,
                                                  @RequestHeader("Authorization") String jwt){
        CartItem cartItem =cartService.updateCartItemQuantity(req.getCartItemId(), req.getQuantity());
        return ResponseEntity.ok(cartItem);
    }

    @DeleteMapping("/cart-item/{id}/remove")
    public ResponseEntity<Cart> removeCartItem(@PathVariable Long id,
                                                           @RequestHeader("Authorization") String jwt){
        Cart cart =cartService.removeItemFromCart(id,jwt);
        return ResponseEntity.ok(cart);
    }

    @PutMapping("/cart/clear")
    public ResponseEntity<Cart> clearCart(@RequestBody UpdateCartItemRequest req,
                                                           @RequestHeader("Authorization") String jwt){
        User user = userService.findUserByJwtToken(jwt);
        Cart cart =cartService.clearCart(user.getId());
        return ResponseEntity.ok(cart);
    }

    @GetMapping("/cart")
    public ResponseEntity<Cart> findUserCart(@RequestHeader("Authorization") String jwt){
        User user = userService.findUserByJwtToken(jwt);
        Cart cart =cartService.findCartByUserId(user.getId());
        return ResponseEntity.ok(cart);
    }

}
