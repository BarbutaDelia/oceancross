package com.example.demo.Model.Exceptions.OnboardActivities;

public class OnboardActivityNotFound extends RuntimeException {

    public OnboardActivityNotFound(Long id) {
        super("Could not find onboard activity " + id);
    }
}