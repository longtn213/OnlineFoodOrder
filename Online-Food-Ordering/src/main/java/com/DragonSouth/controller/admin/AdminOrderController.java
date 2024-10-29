package com.DragonSouth.controller.admin;

import com.DragonSouth.model.Order;
import com.DragonSouth.response.MessageResponse;
import com.DragonSouth.service.OrderService;
import com.DragonSouth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminOrderController {

    private final OrderService orderService;
    private final UserService userService;

    @GetMapping("/order/restaurant/{restaurantId}")
    public ResponseEntity<List<Order>> getOrderHistory(@PathVariable Long restaurantId,
                                                       @RequestParam(required = false) String orderStatus) {
        List<Order> orders = orderService.getRestaurantsOrders(restaurantId,orderStatus);
        return ResponseEntity.ok(orders);
    }

    @PutMapping("/order/update")
    public ResponseEntity<Order> updateOrderStatus(@RequestParam String orderStatus,
                                                   @RequestParam Long orderId) {
        Order orderSaved = orderService.updateOrder(orderId, orderStatus);
        return ResponseEntity.ok(orderSaved);
    }

    @DeleteMapping("/order/cancel/{orderId}")
    public ResponseEntity<?> cancelOrder(@PathVariable Long orderId) {
        orderService.cancelOrder(orderId);
        MessageResponse response = new MessageResponse();
        response.setMessage("Order cancelled successfully");
        return ResponseEntity.ok(response);
    }

}
