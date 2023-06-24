package com.shopping.shopping_cart.dao;

import com.shopping.shopping_cart.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:4200")
@Repository
@RepositoryRestResource(collectionResourceRel = "orders", path = "orders")
public interface OrderRepository extends JpaRepository<Order, Long> {
     }
