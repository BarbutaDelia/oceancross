package com.example.demo.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/cruises")
public class CruiseController {
    @GetMapping("")
    public ResponseEntity<?> getAllCruises() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCruise(@PathVariable Integer id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> addCruise() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCruise(@PathVariable Integer id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{id}/ports")
    public ResponseEntity<?> addCruisePorts() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}/ports/{port_id}")
    public ResponseEntity<?> deleteCruisePort(@PathVariable Integer port_id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{id}/onboardActivities")
    public ResponseEntity<?> addCruiseOnBoardActivities() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}/onboardActivities/{onboardActivity_id}")
    public ResponseEntity<?> deleteCruiseOnBoardActivity(@PathVariable Integer onboardActivity_id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{id}/users")
    public ResponseEntity<?> addCruiseToWishlist(@PathVariable Integer id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getAllCruisesFromWishlist(@PathVariable Integer id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{cruise_id}/port/{port_id}/info")
    public ResponseEntity<?> getPortStopInfo(@PathVariable Integer cruise_id, @PathVariable Integer port_id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @GetMapping("/{cruise_id}/port/{port_id}/activities")
//    public ResponseEntity<?> getAllPortActivities(@PathVariable Integer cruise_id, @PathVariable Integer port_id) {
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

    @GetMapping("/{cruise_id}/users/{user_id}/port/{port_id}/activities")
    public ResponseEntity<?> getUserPortActivities(@PathVariable Integer cruise_id, @PathVariable Integer user_id, @PathVariable Integer port_id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping ("/{cruise_id}/users/{user_id}/port/{port_id}/activities")
    public ResponseEntity<?> addUserPortActivities(@PathVariable Integer cruise_id, @PathVariable Integer user_id, @PathVariable Integer port_id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //in momentul in care utilizatorul o sa faca drag and drop in interfata in port activities page,
    // o sa se apeleze metoda asta de delete pt toata activitatile pe care le avea adaugate utilizatorul inainte, apoi o sa se faca
    // post cu toate activitatile in forma noua, de dupa drag and drop
    @DeleteMapping("/{cruise_id}/users/{user_id}/port/{port_id}/activities")
    public ResponseEntity<?> deleteAllPortActivitiesForUser(@PathVariable Integer cruise_id, @PathVariable Integer user_id, @PathVariable Integer port_id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //nu e asa -- facem diferenta pe frontend si trimiterm cereri de post si delete
}
