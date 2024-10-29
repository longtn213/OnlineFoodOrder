package com.DragonSouth.controller;

import com.DragonSouth.model.Order;
import com.DragonSouth.model.User;
import com.DragonSouth.request.OrderRequest;
import com.DragonSouth.service.OrderService;
import com.DragonSouth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;

    @PostMapping("/order")
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequest order,
                                             @RequestHeader("Authorization") String jwt) {
        User user = userService.findUserByJwtToken(jwt);
        Order orderSaved = orderService.createOrder(order, user);
        return ResponseEntity.ok(orderSaved);
    }

    @GetMapping("/order/user")
    public ResponseEntity<List<Order>> getOrderHistory(@RequestHeader("Authorization") String jwt) {
        User user = userService.findUserByJwtToken(jwt);
        List<Order> orders = orderService.getUserOrders(user.getId());
        return ResponseEntity.ok(orders);
    }
}
