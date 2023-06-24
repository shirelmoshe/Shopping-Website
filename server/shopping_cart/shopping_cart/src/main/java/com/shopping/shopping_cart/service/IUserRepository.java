package com.shopping.shopping_cart.service;

import com.shopping.shopping_cart.entity.User;

import java.util.Collection;

public interface IUserRepository {
    Collection<User> listUsers(int page, int pageSize);
    User createUser(User user);
    Boolean deleteUser(Long id);
    User getUserById(Long id);
    User updateUser(User user);
}