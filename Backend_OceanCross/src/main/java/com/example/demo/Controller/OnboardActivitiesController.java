package com.example.demo.Controller;
//TODO: @GRABO fa request class pt onboardActivity( pe aici ai ramas)
import com.example.demo.Model.Entities.Cruise;
import com.example.demo.Model.Entities.OnboardActivity;
import com.example.demo.Model.Entities.Port;
import com.example.demo.Model.Exceptions.Activities.CollectionOfActivitiesNotFound;
import com.example.demo.Model.Exceptions.Cruises.CollectionOfCruisesNotFound;
import com.example.demo.Model.Repositories.OnBoardActivityRepository;
import com.example.demo.Model.Repositories.PortRepository;
import com.example.demo.Model.Services.CruiseService;
import com.example.demo.Model.Services.OnBoardActivityService;
import com.example.demo.View.DTOs.CruiseDto;
import com.example.demo.View.DTOs.Payload.Request.AddPortRequest;
import com.example.demo.View.DTOs.Payload.Response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.example.demo.Model.Util.Conversion.getCruiseDtosFromCruises;

@RestController
@RequestMapping("api/onboardActivities")
public class OnboardActivitiesController {

    
    @Autowired
    private OnBoardActivityService onBoardActivityService;
    @Autowired
    private OnBoardActivityRepository onBoardActivityRepository;
    @GetMapping("")
    public ResponseEntity<?> getAllOnboardActivities() {
        try{
            //TODO: Create DTOs and use them
            List<OnboardActivity> cruises = onBoardActivityService.listAllActivities();
            return new ResponseEntity<>(cruises, HttpStatus.OK);
        }
        catch(CollectionOfActivitiesNotFound e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> addOnboardActivity(@Valid @RequestBody AddPortRequest addPortRequest) {
        if (onBoardActivityRepository.existsByName(addPortRequest.getName())) {
        return ResponseEntity
                .badRequest()
                .body(new MessageResponse("Error: This port is already in database!"));
    }
        //if everything it`s ok, add activity
//        Port port = new Port(addPortRequest.getName(),addPortRequest.getLatitude(), addPortRequest.getLongitude() );
//        onBoardActivityRepository.save(port);
        return ResponseEntity.ok(new MessageResponse("Port added successfully!"));

    }
}
