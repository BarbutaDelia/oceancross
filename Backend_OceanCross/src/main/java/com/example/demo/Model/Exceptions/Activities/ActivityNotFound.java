package com.example.demo.Model.Exceptions.Activities;

public class ActivityNotFound extends RuntimeException {

    public ActivityNotFound(Long id) {
        super("Could not find activity " + id);
    }
}