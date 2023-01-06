package com.example.demo.Model.Exceptions.Activities;

public class CollectionOfActivitiesNotFound extends RuntimeException {
    public CollectionOfActivitiesNotFound() {
            super("Could not find activities!");
        }
}
