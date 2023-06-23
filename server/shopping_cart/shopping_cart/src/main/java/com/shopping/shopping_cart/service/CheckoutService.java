package com.shopping.shopping_cart.service;

import com.shopping.shopping_cart.entity.Order;
import org.springframework.data.domain.Page;

import java.util.Collection;

public interface CheckoutService {
    Order create(Order order);
    Page<Order> list(int page, int pageSize);

    Collection<Order> list(int limit);




    Order get(Long id);

    Order update(Order updatedOrder);
    Boolean delete(Long id);



}

