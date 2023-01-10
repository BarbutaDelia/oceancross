package com.example.demo.Controller;

import com.example.demo.Model.Entities.Cruise;
import com.example.demo.Model.Entities.CruisePort;
import com.example.demo.Model.Entities.OnboardActivity;
import com.example.demo.Model.Entities.Port;
import com.example.demo.Model.Exceptions.Cruises.CollectionOfCruisesNotFound;
import com.example.demo.Model.Exceptions.Cruises.CruiseNotFound;
import com.example.demo.Model.Exceptions.Cruises.CruisePortNotFound;
import com.example.demo.Model.Exceptions.OnboardActivities.OnboardActivityNotFound;
import com.example.demo.Model.Exceptions.Ports.PortNotFound;
import com.example.demo.Model.Repositories.CruisePortsRepository;
import com.example.demo.Model.Repositories.CruiseRepository;
import com.example.demo.Model.Repositories.OnBoardActivityRepository;
import com.example.demo.Model.Repositories.PortRepository;
import com.example.demo.Model.Services.CruiseService;
import com.example.demo.Model.Services.OnBoardActivityService;
import com.example.demo.Model.Services.PortService;
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
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static com.example.demo.Model.Util.Conversion.*;

@RestController
@RequestMapping("api/cruises")
public class CruiseController {
    @Autowired
    private CruiseService cruiseService;
    @Autowired
    private OnBoardActivityService onBoardActivityService;
    @Autowired
    private PortService portService;
    @Autowired
    private CruisePortsRepository cruisePortsRepository;

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

    @PostMapping("") @Transactional
    public ResponseEntity<Object> createCruise(@RequestBody CruiseRequest cruiseRequest) {
        // Validate the request body
        if (cruiseRequest.getName() == null || cruiseRequest.getStart_date() == null || cruiseRequest.getEnd_date() == null ||
                cruiseRequest.getPrice() == null || cruiseRequest.getOnboardActivities() == null || cruiseRequest.getCruisePorts() == null) {
            return new ResponseEntity<>("Missing required fields", HttpStatus.BAD_REQUEST);
        }

        try{


        // Create the new cruise
        Cruise cruise = new Cruise();
        cruise.setName(cruiseRequest.getName());
        cruise.setStart_date(cruiseRequest.getStart_date());
        cruise.setEnd_date(cruiseRequest.getEnd_date());
        cruise.setPrice(cruiseRequest.getPrice());

        // Save the cruise to the database
        Cruise savedCruise = cruiseService.saveCruise(cruise);

        // Save the onboard activities and cruise ports to the database
        List<OnboardActivity> onboardActivities = getOnboardActivitiesFromOnboardActivityDTOs(cruiseRequest.getOnboardActivities(), savedCruise);
        onBoardActivityService.saveActivities(onboardActivities);
        cruise.setOnboardActivities(onboardActivities);

        List<CruisePort> cruisePorts = getCruisePortsFromCruisePortRequests(cruiseRequest.getCruisePorts(), savedCruise, portService);
        cruiseService.saveCruisePorts(cruisePorts);
        cruise.setCruisePorts(cruisePorts);

        // Return the new cruise
        return new ResponseEntity<>(savedCruise, HttpStatus.OK);
        }
        catch (RuntimeException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}") // @Transactional
    public ResponseEntity<Object> updateCruise(@PathVariable long id, @RequestBody CruiseRequest cruiseDto) {
        // Validate the request body
        if (cruiseDto.getName() == null || cruiseDto.getStart_date() == null || cruiseDto.getStart_date() == null ||
                cruiseDto.getPrice() == null || cruiseDto.getOnboardActivities() == null || cruiseDto.getCruisePorts() == null) {
            return new ResponseEntity<>("Missing required fields", HttpStatus.BAD_REQUEST);
        }
        try {
            Cruise cruise = cruiseService.getCruise(id);
            cruise.setName(cruiseDto.getName());
            cruise.setStart_date(cruiseDto.getStart_date());
            cruise.setEnd_date(cruiseDto.getEnd_date());
            cruise.setPrice(cruiseDto.getPrice());

            cruiseService.saveCruise(cruise);

            List<OnboardActivity> onboardActivities = getOnboardActivitiesFromOnboardActivityDTOsPUT(cruiseDto.getOnboardActivities(), cruise,onBoardActivityService);
            for (OnboardActivity onboardActivity:onboardActivities
                 ) {
                onBoardActivityService.saveActivity(onboardActivity);
            }


            List<CruisePort> cruisePorts = getCruisePortsFromCruisePortRequestsPUT(cruiseDto.getCruisePorts(), cruise, portService, cruiseService);

            for (CruisePort cruisePort: cruisePorts
                 ) {
                cruiseService.saveCruisePort(cruisePort);
            }

            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
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

    @GetMapping("/{cruise_id}/port/{port_id}")
    public ResponseEntity<?> getPortCruiseByCruiseIdAndPortId(@PathVariable Long port_id,@PathVariable Long cruise_id)
    {
        try {
            Cruise cruise=cruiseService.getCruise(cruise_id);
            List<CruisePort> cruisePorts=cruise.getCruisePorts();
            for (CruisePort cp:cruisePorts)
            {
                if(cp.getPort().getId()==port_id)
                {
                    return new ResponseEntity<>(cp,HttpStatus.OK);
                }
            }
            //   CruisePort cruisePort = cruiseService.getCruisePort(port_id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch(PortNotFound e)
        {
            return   new ResponseEntity<>(e,HttpStatus.NOT_FOUND);
        }
        catch (CruiseNotFound e)
        {
            return  new ResponseEntity<>(e,HttpStatus.NOT_FOUND);
        }

    }

    //nu e asa -- facem diferenta pe frontend si trimiterm cereri de post si delete
}
