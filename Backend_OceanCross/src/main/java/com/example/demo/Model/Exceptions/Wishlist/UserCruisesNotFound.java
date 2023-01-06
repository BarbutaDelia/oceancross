package com.example.demo.Model.Exceptions.Wishlist;

public class UserCruisesNotFound extends RuntimeException {

    public UserCruisesNotFound(Long id) {
        super("Could not find port " + id);
    }
}