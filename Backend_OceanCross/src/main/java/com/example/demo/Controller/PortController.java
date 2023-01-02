package com.example.demo.Controller;

import com.example.demo.Model.Entities.Port;
import com.example.demo.Model.Exceptions.Ports.CollectionOfPortsNotFound;
import com.example.demo.Model.Services.PortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/ports")
public class PortController {

    @Autowired
    private PortService portService;

    @GetMapping("")
    public ResponseEntity<?> getAllPorts() {
        try{
            List<Port> ports = portService.listAllPorts();
           // List<CruiseDto> cruiseDtos = getCruiseDtosFromCruises(cruises);
            return new ResponseEntity<>(ports, HttpStatus.OK);
        }
        catch(CollectionOfPortsNotFound e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
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
