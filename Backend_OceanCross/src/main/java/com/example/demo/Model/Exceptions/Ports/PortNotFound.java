package com.example.demo.Model.Exceptions.Ports;

public class PortNotFound extends RuntimeException {

    public PortNotFound(Long id) {
        super("Could not find port " + id);
    }
}