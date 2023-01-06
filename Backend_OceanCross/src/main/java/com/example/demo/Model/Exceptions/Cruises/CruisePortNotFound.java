package com.example.demo.Model.Exceptions.Cruises;

public class CruisePortNotFound extends RuntimeException {

    public CruisePortNotFound(Long id) {
        super("Could not find cruise port " + id);
    }
}