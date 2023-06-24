package com.shopping.shopping_cart.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    //extends WebSecurityConfigurerAdapter

    /*
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // Configure in-memory authentication with username and encoded password
        auth.inMemoryAuthentication()
                .withUser("user")
                .password(passwordEncoder().encode("password"))
                .roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Disable CSRF protection, allow public access to "/products" endpoint,
        // and require authentication for other endpoints
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/products/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }
    */

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // Configure in-memory authentication with username, password, and authorities
        auth.inMemoryAuthentication()
                .withUser("admin").password("{noop}admin").authorities("admin")
                .and()
                .withUser("user").password("{noop}user").authorities("user");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Console log to indicate that the SecurityConfig bean has been created
    @PostConstruct
    public void init() {
        System.out.println("SecurityConfig bean has been created.");
    }
}
