package com.shopping.shopping_cart.entity;

import com.shopping.shopping_cart.model.Rolse;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Profile;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.Date;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;


    @Enumerated(EnumType.STRING)
    @Column(name = "user_type")
    private Rolse userType;

    @Column(name = "password")
    private String password;


    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    private Set<String> roles;

    // Constructors, Getters, and Setters

    public User() {
    }

    public User(String name, String email, Rolse userType, String password) {
        this.name = name;
        this.email = email;
        this.userType = userType;
        this.password = password;

    }
    private Date resetTokenExpiry;

    public void setResetTokenExpiry(Date resetTokenExpiry) {
        this.resetTokenExpiry = resetTokenExpiry;
    }



        // Other properties and methods

        private String resetToken;

        public void setResetToken(String resetToken) {
            this.resetToken = resetToken;
        }

        // Getter and other methods



    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Rolse getUserType() {
        return userType;
    }

    public void setUserType(Rolse userType) {
        this.userType = userType;
    }

    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
