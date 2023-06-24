package com.shopping.shopping_cart.rest;

import com.shopping.shopping_cart.entity.Order;
import com.shopping.shopping_cart.service.implementation.CheckoutService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final CheckoutService checkoutService;

    public OrderController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    // Retrieve all orders
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        System.out.println("Retrieving all orders...");
        List<Order> orders = checkoutService.getAllOrders();
        System.out.println("Total orders found: " + orders.size());
        return ResponseEntity.ok(orders);
    }

    // Retrieve an order by ID
    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long orderId) {
        System.out.println("Retrieving order with ID: " + orderId);
        Optional<Order> order = checkoutService.getOrderById(orderId);
        if (order.isPresent()) {
            System.out.println("Order found: " + order.get());
            return ResponseEntity.ok(order.get());
        } else {
            System.out.println("Order not found.");
            return ResponseEntity.notFound().build();
        }
    }

    // Create a new order
    @PostMapping
    public ResponseEntity<Order> saveOrder(@RequestBody Order order) {
        System.out.println("Saving new order: " + order);
        Order savedOrder = checkoutService.saveOrder(order);
        System.out.println("New order saved with ID: " + savedOrder.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(savedOrder);
    }

    // Delete an order by ID
    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) {
        System.out.println("Deleting order with ID: " + orderId);
        checkoutService.deleteOrder(orderId);
        System.out.println("Order deleted successfully.");
        return ResponseEntity.noContent().build();
    }

    // Add more endpoints and methods for order-related operations as needed
}
