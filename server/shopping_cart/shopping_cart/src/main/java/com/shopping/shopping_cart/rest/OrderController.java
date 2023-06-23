package com.shopping.shopping_cart.rest;

import com.shopping.shopping_cart.entity.Order;
import com.shopping.shopping_cart.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

public class OrderController {
    private final CheckoutService checkoutService;

    @Autowired
    public OrderController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order createdOrder = checkoutService.create(order);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }
}
