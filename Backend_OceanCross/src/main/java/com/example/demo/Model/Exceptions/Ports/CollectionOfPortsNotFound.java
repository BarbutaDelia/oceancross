package com.example.demo.Model.Exceptions.Ports;

public class CollectionOfPortsNotFound extends RuntimeException {
    public CollectionOfPortsNotFound() {
            super("Could not find ports!");
        }
}
