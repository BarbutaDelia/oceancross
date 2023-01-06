package com.example.demo.Controller;

import com.example.demo.Model.Entities.Cruise;
import com.example.demo.Model.Entities.CruisePort;
import com.example.demo.Model.Entities.OnboardActivity;
import com.example.demo.Model.Entities.Port;
import com.example.demo.Model.Exceptions.Cruises.CollectionOfCruisesNotFound;
import com.example.demo.Model.Exceptions.Cruises.CruiseNotFound;
import com.example.demo.Model.Exceptions.Cruises.CruisePortNotFound;
import com.example.demo.Model.Exceptions.OnboardActivities.OnboardActivityNotFound;
import com.example.demo.Model.Repositories.CruisePortsRepository;
import com.example.demo.Model.Repositories.CruiseRepository;
import com.example.demo.Model.Repositories.OnBoardActivityRepository;
import com.example.demo.Model.Repositories.PortRepository;
import com.example.demo.Model.Services.CruiseService;
import com.example.demo.View.DTOs.CruiseDto;
import com.example.demo.View.DTOs.CruisePortDto;
import com.example.demo.View.DTOs.OnboardActivityDTO;
import com.example.demo.View.DTOs.Payload.Request.CruiseRequest;
import com.example.demo.View.DTOs.Payload.Request.CruisePortRequest;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.io.Console;
import java.util.ArrayList;
import java.util.List;

import static com.example.demo.Model.Util.Conversion.getCruiseDtoFromCruise;
import static com.example.demo.Model.Util.Conversion.getCruiseDtosFromCruises;

@RestController
@RequestMapping("api/cruises")
public class CruiseController {
    @Autowired
    private CruiseService cruiseService;
    @Autowired
    private PortRepository portRepository;
    @Autowired
    private CruisePortsRepository cruisePortRepository;
    @Autowired
    private OnBoardActivityRepository onboardActivityRepository;
    @Autowired
    private CruiseRepository cruiseRepository;

    @GetMapping("")
    public ResponseEntity<?> getAllCruises(){
        try{
            List<Cruise> cruises = cruiseService.listAllCruises();
            List<CruiseDto> cruiseDtos = getCruiseDtosFromCruises(cruises);
            return new ResponseEntity<>(cruiseDtos, HttpStatus.OK);
        }
        catch(CollectionOfCruisesNotFound e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCruise(@PathVariable Long id){
        try{
            Cruise cruise = cruiseService.getCruise(id);
            CruiseDto cruiseDto = getCruiseDtoFromCruise(cruise);
            return new ResponseEntity<>(cruiseDto, HttpStatus.OK);
        }
        catch(CruiseNotFound e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<?> getCruise(@PathVariable Integer id) {
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//TODO: De scos metoda veche de post dupa ce merge asta
//    @PostMapping(value = "")
//    public ResponseEntity<?> addCruise(@Valid @RequestBody CruiseRequest cruise) {
//
//        System.out.println(cruise);
//
//        return new ResponseEntity<>(cruise, HttpStatus.OK);
//    }
@PostMapping("") @Transactional
public ResponseEntity<Object> createCruise(@RequestBody CruiseRequest cruiseRequest) {
    // Validate the request body
    if (cruiseRequest.getName() == null || cruiseRequest.getStart_date() == null || cruiseRequest.getEnd_date() == null ||
            cruiseRequest.getPrice() == null || cruiseRequest.getOnboardActivities() == null || cruiseRequest.getCruisePorts() == null) {
        return new ResponseEntity<>("Missing required fields", HttpStatus.BAD_REQUEST);
    }
    //TODO: @Grabo fix arrival time and all time related staff format
    // Create the new cruise
    Cruise cruise = new Cruise();
    cruise.setName(cruiseRequest.getName());
    cruise.setStart_date(cruiseRequest.getStart_date());
    cruise.setEnd_date(cruiseRequest.getEnd_date());
    cruise.setPrice(cruiseRequest.getPrice());

    // Save the cruise to the database
    Cruise savedCruise = cruiseRepository.save(cruise);

    // Save the onboard activities and cruise ports to the database
    List<OnboardActivity> onboardActivities = new ArrayList<>();
    for (OnboardActivityDTO onboardActivityDto : cruiseRequest.getOnboardActivities()) {
        OnboardActivity onboardActivity = new OnboardActivity();
        onboardActivity.setCruise(savedCruise);
        onboardActivity.setName(onboardActivityDto.getName());
        onboardActivity.setStart_date(onboardActivityDto.getStart_date());
        onboardActivity.setStart_time(onboardActivityDto.getStart_time());
        onboardActivity.setDuration(onboardActivityDto.getDuration());
        onboardActivity.setLocation(onboardActivityDto.getLocation());
        onboardActivities.add(onboardActivity);
       // System.out.println(onboardActivity.toString());
    }
    onboardActivityRepository.saveAll(onboardActivities);
    cruise.setOnboardActivities(onboardActivities);

    List<CruisePort> cruisePorts = new ArrayList<>();
    for (CruisePortRequest cruisePortDto : cruiseRequest.getCruisePorts()) {
        CruisePort cruisePort = new CruisePort();
        Port port = portRepository.findById(cruisePortDto.getId()).get();
        System.out.println(port.getName() + port.getId());
        System.out.println(port.to_string());

        cruisePort.setPort(port);
        cruisePort.setCruise(savedCruise);
        cruisePort.setArrival_date(cruisePortDto.getArrival_date());
        cruisePort.setArrival_time(cruisePortDto.getArrival_time());
        cruisePort.setDuration(cruisePortDto.getDuration());
        cruisePorts.add(cruisePort);
    }
    cruisePortRepository.saveAll(cruisePorts);
    cruise.setCruisePorts(cruisePorts);

    // Return the new cruise
    return new ResponseEntity<>(savedCruise, HttpStatus.OK);
}


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCruise(@PathVariable Long id) {
        try{
            cruiseService.deleteCruise(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(CruiseNotFound e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{id}/ports")
    public ResponseEntity<?> addCruisePorts() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}/ports/{cruisePort_id}")
    public ResponseEntity<?> deleteCruisePort(@PathVariable Long cruisePort_id) {
        try{
            cruiseService.deleteCruisePort(cruisePort_id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(CruisePortNotFound e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{id}/onboardActivities")
    public ResponseEntity<?> addCruiseOnBoardActivities() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}/onboardActivities/{onboardActivity_id}")
    public ResponseEntity<?> deleteCruiseOnBoardActivity(@PathVariable Long onboardActivity_id) {
        try{
            cruiseService.deleteCruiseOnBoardActivity(onboardActivity_id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(OnboardActivityNotFound e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
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
