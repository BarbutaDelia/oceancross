package com.example.demo.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/portActivitiesSchedule")
public class PortActivitiesScheduleController {
    @GetMapping("")
    public ResponseEntity<?> getAllPortActivitiesSchedule() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> addPortActivitiesSchedule() {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
