package com.example.demo.Model.Exceptions;

public class CruiseNotFound extends RuntimeException {

    public CruiseNotFound(Long id) {
        super("Could not find cruise " + id);
    }
}