package com.example.demo.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/onboardActivities")
public class OnboardActivitiesController {
    @GetMapping("")
    public ResponseEntity<?> getAllOnboardActivities() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> addOnboardActivity() {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
