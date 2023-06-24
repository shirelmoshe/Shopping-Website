package com.shopping.shopping_cart.service.implementation;

import com.shopping.shopping_cart.dao.OrderRepository;
import com.shopping.shopping_cart.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CheckoutService {

    private final OrderRepository orderRepository;

    @Autowired
    public CheckoutService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // Get all orders
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Get an order by ID
    public Optional<Order> getOrderById(Long orderId) {
        return orderRepository.findById(orderId);
    }

    // Save an order
    public Order saveOrder(Order order) {
        // Perform necessary validation or business logic
        if (order == null) {
            throw new IllegalArgumentException("Invalid order");
        }
        // Save the order in the repository
        Order savedOrder = orderRepository.save(order);
        // Add logs
        System.out.println("Order saved: " + savedOrder);
        return savedOrder;
    }

    // Delete an order
    public void deleteOrder(Long orderId) {
        // Perform necessary validation or business logic
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isEmpty()) {
            throw new IllegalArgumentException("Order not found with ID: " + orderId);
        }
        // Delete the order from the repository
        orderRepository.deleteById(orderId);
        // Add logs
        System.out.println("Order deleted with ID: " + orderId);
    }

    // Other order-related methods (e.g., order management, status updates)

    // Example of a test case
    public void performOrderCheckout(Long orderId) {
        Optional<Order> orderOptional = getOrderById(orderId);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            // Perform order checkout logic
            System.out.println("Order checkout performed for order with ID: " + orderId);
        } else {
            throw new IllegalArgumentException("Order not found with ID: " + orderId);
        }
    }
}
