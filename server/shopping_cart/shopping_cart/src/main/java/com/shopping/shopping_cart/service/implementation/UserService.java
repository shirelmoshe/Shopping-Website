package com.shopping.shopping_cart.service.implementation;

import com.shopping.shopping_cart.dao.UserRepository;
import com.shopping.shopping_cart.entity.User;
import com.shopping.shopping_cart.exception.UserNotFoundException;
import com.shopping.shopping_cart.model.Rolse;
import com.shopping.shopping_cart.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, EmailService emailService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
    }

    public User updateUser(Long userId, User updatedUser) {
        User user = getUserById(userId);

        user.setName(updatedUser.getName());
        user.setEmail(updatedUser.getEmail());
        // Update other properties as needed

        User updated = userRepository.save(user);
        // Add logs
        System.out.println("User updated: " + updated);
        return updated;
    }

    public void deleteUser(Long userId) {
        User user = getUserById(userId);
        userRepository.delete(user);
        // Add logs
        System.out.println("User deleted with ID: " + userId);
    }

    public User registerUser(User user) {
        Rolse userType = user.getUserType();
        Set<String> roles = new HashSet<>();

        if (userType == Rolse.user) {
            roles.add("user");
        } else if (userType == Rolse.admin) {
            roles.add("admin");
        }

        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User registeredUser = userRepository.save(user);
        // Add logs
        System.out.println("User registered: " + registeredUser);
        return registeredUser;
    }

    public User getUserProfile(Long userId) {
        return getUserById(userId);
    }

    public User authenticateUser(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return user;
        }
        throw new IllegalArgumentException("Invalid email or password");
    }

    public String requestPasswordReset(Long userId) {
        User user = getUserById(userId);

        UUID token = UUID.randomUUID();
        String resetToken = token.toString();

        user.setResetToken(resetToken);
        user.setResetTokenExpiry(getResetTokenExpiry());
        userRepository.save(user);

        sendPasswordResetNotification(user.getEmail(), resetToken);

        return resetToken;
    }

    private void sendPasswordResetNotification(String email, String resetToken) {
        String subject = "Password Reset";
        String body = "Dear user,\n\nYou have requested a password reset. Please use the following token: " + resetToken;
        emailService.sendEmail(email, subject, body);
    }

    private Date getResetTokenExpiry() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, 1);
        return calendar.getTime();
    }
}
