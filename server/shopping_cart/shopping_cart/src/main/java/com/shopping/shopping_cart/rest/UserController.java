package com.shopping.shopping_cart.rest;

import com.shopping.shopping_cart.entity.User;
import com.shopping.shopping_cart.service.implementation.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin("http://localhost:4200")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Register a new user
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        System.out.println("Registering new user: " + user);
        User registeredUser = userService.registerUser(user);
        System.out.println("User registered successfully.");
        return ResponseEntity.ok(registeredUser);
    }

    // Login a user
    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody User user) {
        System.out.println("Logging in user: " + user.getEmail());
        User authenticatedUser = userService.authenticateUser(user.getEmail(), user.getPassword());
        System.out.println("User logged in successfully.");
        return ResponseEntity.ok(authenticatedUser);
    }

    // Get a user by ID
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId)  {
        System.out.println("Retrieving user with ID: " + userId);
        User user = userService.getUserById(userId);
        System.out.println("User found: " + user);
        return ResponseEntity.ok(user);
    }

    // Update a user
    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User user) {
        System.out.println("Updating user with ID: " + userId);
        User updatedUser = userService.updateUser(userId, user);
        System.out.println("User updated: " + updatedUser);
        return ResponseEntity.ok(updatedUser);
    }

    // Delete a user
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        System.out.println("Deleting user with ID: " + userId);
        userService.deleteUser(userId);
        System.out.println("User deleted successfully.");
        return ResponseEntity.noContent().build();
    }

    // Get user profile
    @GetMapping("/{userId}/profile")
    public ResponseEntity<User> getUserProfile(@PathVariable Long userId) {
        System.out.println("Retrieving profile of user with ID: " + userId);
        User userProfile = userService.getUserProfile(userId);
        System.out.println("User profile found: " + userProfile);
        return ResponseEntity.ok(userProfile);
    }

    // Request password reset
    @PostMapping("/{userId}/password/reset")
    public ResponseEntity<Void> requestPasswordReset(@PathVariable Long userId) {
        System.out.println("Requesting password reset for user with ID: " + userId);
        String resetToken = userService.requestPasswordReset(userId);
        // Handle the resetToken if needed
        System.out.println("Password reset token: " + resetToken);
        return ResponseEntity.ok().build();
    }
}
