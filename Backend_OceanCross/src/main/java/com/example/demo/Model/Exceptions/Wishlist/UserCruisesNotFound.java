package com.example.demo.Model.Exceptions.Wishlist;

public class UserCruisesNotFound extends RuntimeException {

    public UserCruisesNotFound(Long id) {
        super("User" + id+"dose not have any cruises in wishlist");
    }
}