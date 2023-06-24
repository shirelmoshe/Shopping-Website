package com.shopping.shopping_cart.exception;

public class UserNotFoundException extends  RuntimeException {
    public UserNotFoundException(String message){
        super(message);
    }
}
