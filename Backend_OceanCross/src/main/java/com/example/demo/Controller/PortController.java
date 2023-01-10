package com.example.demo.Controller;

import com.example.demo.Model.Entities.DecodedMultipartFile;
import com.example.demo.Model.Entities.Port;
import com.example.demo.Model.Entities.PortActivities;
import com.example.demo.Model.Entities.User;
import com.example.demo.Model.Exceptions.Ports.CollectionOfPortsNotFound;
import com.example.demo.Model.Repositories.PortActivitiesRepository;
import com.example.demo.Model.Repositories.PortRepository;
import com.example.demo.Model.Repositories.UserRepository;
import com.example.demo.Model.Services.FileStorageService;
import com.example.demo.Model.Services.PortService;
import com.example.demo.Model.Util.FileUploadUtil;
import com.example.demo.View.DTOs.Payload.Request.AddPortRequest;
import com.example.demo.View.DTOs.Payload.Request.DeletePortActivityRequest;
import com.example.demo.View.DTOs.Payload.Request.PortActivityRequest;
import com.example.demo.View.DTOs.Payload.Response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;


@RestController
@RequestMapping("api/ports")
public class PortController {

    @Autowired
    private PortService portService;
    @Autowired
    private PortRepository portRepository;

    @Autowired
    private PortActivitiesRepository portActivitiesRepository;

    @Autowired
    private FileStorageService fileStorageService;


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
    public ResponseEntity<?> getAllPortActivitiesAddedByOneVendor(@PathVariable Long port_id, @PathVariable Long user_id) {
        //stiu sigur ca exista portul si user-ul
        List<PortActivities> portActivities  = portActivitiesRepository.findByPortIdAndUserId(port_id, user_id);
        if (portActivities.isEmpty())
        {
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(portActivities, HttpStatus.OK);
    }

    @PostMapping("/{port_name}/users/{user_id}/activities")
    public ResponseEntity<?> addPortActivities(@PathVariable String port_name, @PathVariable Long user_id,
                                               @Valid @RequestBody PortActivityRequest portActivityRequest)
    {

        if (portRepository.findByName(port_name) != null)
        {
            Port port = portRepository.findByName(port_name);

            PortActivities portActivity = new PortActivities();
            portActivity.setUserId(user_id);
            portActivity.setPortId(port.getId());
            portActivity.setName(portActivityRequest.getName());


            byte[] image = Base64.getDecoder().decode(portActivityRequest.getImage());
            DecodedMultipartFile file = new DecodedMultipartFile(image);
            String fileName = fileStorageService.storeFile(file, portActivityRequest.getImage_name());

            String location = System.getProperty("user.dir") + "\\uploads";
            System.out.println(location);
            System.out.println("calea catre imagine: " + "uploads/files/"+fileName);
            portActivity.setImagePath("uploads/files/"+fileName);
            portActivitiesRepository.save(portActivity);

            return ResponseEntity.ok(portActivitiesRepository.findByName(portActivity.getName()).getId());
        }
        else
        {
            return new ResponseEntity<>("Could not find port", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{port_id}/users/{user_id}/activities/{port_activity_id}")
    public ResponseEntity<?> deletePortActivity(@PathVariable Long port_id, @PathVariable Long user_id, @PathVariable Long port_activity_id)
    {
        //teoretic nu ar trebui sa existe probleme; activitatea ar trebui sa existe
        try{
            portActivitiesRepository.deleteById(port_activity_id);
            //portActivitiesRepository.deleteByNameAndUserIdAndPortId(deletePortActivityRequest.getName(), user_id, port_id);
            return new ResponseEntity<>("Action completed successfully", HttpStatus.OK);
        }catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
