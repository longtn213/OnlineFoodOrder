package com.DragonSouth.service;

import com.DragonSouth.model.Order;
import com.DragonSouth.model.User;
import com.DragonSouth.request.OrderRequest;

import java.util.List;

public interface OrderService {

    public Order createOrder(OrderRequest order, User user);

    public Order updateOrder(Long orderId, String orderStatus);

    void cancelOrder(Long orderId);

    public List<Order> getUserOrders(Long userId);

    public List<Order> getRestaurantsOrders(Long restaurantId, String orderStatus);

    public Order findOrderById(Long orderId);

}
