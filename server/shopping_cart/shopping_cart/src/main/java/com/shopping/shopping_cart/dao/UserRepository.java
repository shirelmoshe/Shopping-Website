package com.shopping.shopping_cart.dao;

import com.shopping.shopping_cart.entity.User;
import com.shopping.shopping_cart.model.Rolse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@CrossOrigin("http://localhost:4200")
@RepositoryRestResource(collectionResourceRel = "users", path = "users")
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(@Param("email") String email);

    List<User> findByNameContainingIgnoreCase(String name);

    Page<User> findAll(Pageable pageable);

    @Query("SELECT u FROM User u WHERE u.email LIKE %:keyword% OR u.name LIKE %:keyword%")
    List<User> searchUsers(@Param("keyword") String keyword);




}
