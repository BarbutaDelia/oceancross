package com.example.demo.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/ports")
public class PortController {
    @GetMapping("")
    public ResponseEntity<?> getAllPorts() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> addPort() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{port_id}/activities")
    public ResponseEntity<?> getAllPortActivities(@PathVariable Integer port_id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{port_id}/users/{user_id}/activities")
    public ResponseEntity<?> getAllPortActivitiesAddedByOneVendor(@PathVariable Integer port_id, @PathVariable Integer user_id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{port_id}/users/{user_id}/activities")
    public ResponseEntity<?> addPortActivities(@PathVariable Integer port_id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
