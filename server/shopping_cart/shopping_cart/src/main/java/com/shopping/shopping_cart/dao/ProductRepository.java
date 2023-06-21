package com.shopping.shopping_cart.dao;

import com.shopping.shopping_cart.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.data.domain.Pageable;

@CrossOrigin("http://localhost:4200")
@RepositoryRestResource(collectionResourceRel = "products", path = "products", excerptProjection = Product.class)
public interface ProductRepository extends JpaRepository<Product, Integer> {
        Page<Product> findByCategoryId(@Param("id") Integer id, Pageable pageable);
        Page<Product> findByNameContaining(@Param("name") String name,Pageable page);
}
