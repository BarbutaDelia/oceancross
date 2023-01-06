package com.example.demo.Controller;

import com.example.demo.Model.Entities.Port;
import com.example.demo.Model.Entities.User;
import com.example.demo.Model.Exceptions.Ports.CollectionOfPortsNotFound;
import com.example.demo.Model.Repositories.PortRepository;
import com.example.demo.Model.Services.PortService;
import com.example.demo.View.DTOs.Payload.Request.AddPortRequest;
import com.example.demo.View.DTOs.Payload.Response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("api/ports")
public class PortController {

    @Autowired
    private PortService portService;
    @Autowired
    private PortRepository portRepository;

    @GetMapping("")
    public ResponseEntity<?> getAllPorts() {
        try{
            List<Port> ports = portService.listAllPorts();
            return new ResponseEntity<>(ports, HttpStatus.OK);
        }
        catch(CollectionOfPortsNotFound e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> addPort(@Valid @RequestBody AddPortRequest addPortRequest) {
        if (portRepository.existsByName(addPortRequest.getName())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: This port is already in database!"));
        }
        //if everything it`s ok, create the port
        Port port = new Port(addPortRequest.getName(),addPortRequest.getLatitude(), addPortRequest.getLongitude() );
        portRepository.save(port);
        return ResponseEntity.ok(new MessageResponse("Port added successfully!"));
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
