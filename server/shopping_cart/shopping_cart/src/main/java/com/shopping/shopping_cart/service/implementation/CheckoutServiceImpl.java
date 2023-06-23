package com.shopping.shopping_cart.service.implementation;

import com.shopping.shopping_cart.dao.OrderRepository;
import com.shopping.shopping_cart.entity.Order;
import com.shopping.shopping_cart.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CheckoutServiceImpl implements CheckoutService {
    private final OrderRepository orderRepository;

    @Autowired
    public CheckoutServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order create(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Page<Order> list(int page, int pageSize) {
        return null;
    }

    @Override
    public Collection<Order> list(int limit) {
        // Implement the logic to fetch and return a collection of orders
        return orderRepository.findAll(); // Example implementation, you can customize it as per your needs
    }



    @Override
    public Order get(Long id) {
        return orderRepository.findById(id).orElse(null);
    }



    @Override
    public Order update(Order updatedOrder) {
        return orderRepository.save(updatedOrder);
    }


    @Override
    public Boolean delete(Long id) {
        orderRepository.deleteById(id);
        return true;
    }
}
