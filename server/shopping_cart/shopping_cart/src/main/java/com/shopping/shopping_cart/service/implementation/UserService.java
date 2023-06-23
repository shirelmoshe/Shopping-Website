package com.shopping.shopping_cart.service.implementation;

import com.shopping.shopping_cart.dao.UserRepository;
import com.shopping.shopping_cart.entity.User;
import com.shopping.shopping_cart.exception.ApiException;
import com.shopping.shopping_cart.service.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserService implements IUserRepository {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Collection<User> listUsers(int page, int pageSize) {
        return null;
    }

    public User createUser(User user) {
        try {
            // Check if user with the same email already exists
            User existingUser = userRepository.findByEmail(user.getEmail());
            if (existingUser != null) {
                throw new ApiException("User with the same email already exists");
            }

            // Save the new user
            User createdUser = userRepository.save(user);

            // Retrieve the ID of the created user
            int userId = createdUser.getUserId();

            // Set the ID of the created user
            createdUser.setUserId(userId);

            return createdUser;
        } catch (Exception ex) {
            throw new ApiException("Failed to create user");
        }
    }



    @Override
    public Boolean deleteUser(Long id) {
        return null;
    }

    public Collection<User> getAllUsers() {
        try {
            return userRepository.findAll();
        } catch (Exception ex) {
            throw new ApiException("Failed to retrieve user list");
        }
    }

    public User getUserById(Long id) {
        try {
            return userRepository.findById(id)
                    .orElseThrow(() -> new ApiException("User not found"));
        } catch (Exception ex) {
            throw new ApiException("Failed to retrieve user");
        }
    }

    public User updateUser(User user) {
        try {
            return userRepository.save(user);
        } catch (Exception ex) {
            throw new ApiException("Failed to update user");
        }
    }

    public void deleteUserById(Long id) {
        try {
            userRepository.deleteById(id);
        } catch (Exception ex) {
            throw new ApiException("Failed to delete user");
        }
    }
}
