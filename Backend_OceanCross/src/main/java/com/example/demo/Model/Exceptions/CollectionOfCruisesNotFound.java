package com.example.demo.Model.Exceptions;

public class CollectionOfCruisesNotFound extends RuntimeException {
    public CollectionOfCruisesNotFound() {
            super("Could not find cruises.");
        }
}
