package com.DragonSouth.service;

import com.DragonSouth.model.Cart;
import com.DragonSouth.model.CartItem;
import com.DragonSouth.request.AddCartItemRequest;

public interface CartService {
    public CartItem addItemToCart(AddCartItemRequest req, String jwt);

    public CartItem updateCartItemQuantity(Long cartItemId, int quantity);

    public Cart removeItemFromCart(Long cartItemId, String jwt);

    public Long calculateCartTotals(Cart cart);

    public Cart findCartById(Long id);

    public Cart findCartByUserId(String jwt);

    public Cart clearCart(String jwt);
}
