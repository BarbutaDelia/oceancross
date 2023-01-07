package com.example.demo.Controller;

import com.example.demo.Model.Entities.PortActivitiesSchedule;
import com.example.demo.Model.Repositories.PortActivitiesScheduleRepository;
import com.example.demo.View.DTOs.Payload.Request.PortActivityScheduleRequest;
import com.example.demo.View.DTOs.Payload.Response.PortActivityScheduleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.example.demo.Model.Util.Conversion.PortActivityScheduleTOPortActivityScheduleResponse;

@RestController
@RequestMapping("api/portActivitiesSchedule")
public class PortActivitiesScheduleController {
    @Autowired
    PortActivitiesScheduleRepository portActivitiesScheduleRepository;

    @GetMapping("/{port_activity_id}")
    public ResponseEntity<?> getAllPortActivitiesSchedule(@PathVariable Long port_activity_id) {
        //imi returneaza port activity schedule pentru port activity id
        List<PortActivitiesSchedule> portActivitiesScheduleList =  portActivitiesScheduleRepository.findByPortActivityId(port_activity_id);
        List<PortActivityScheduleResponse> portActivityScheduleResponses = PortActivityScheduleTOPortActivityScheduleResponse(portActivitiesScheduleList);

        return new ResponseEntity<>(portActivityScheduleResponses, HttpStatus.OK);
    }

    @PostMapping("/{port_activity_id}")
    public ResponseEntity<?> addPortActivitiesSchedule(@PathVariable Long port_activity_id, @Valid @RequestBody PortActivityScheduleRequest portActivityScheduleRequest) {

        PortActivitiesSchedule portActivitiesSchedule = new PortActivitiesSchedule();

        portActivitiesSchedule.setPortActivityId(port_activity_id);
        portActivitiesSchedule.setDuration(portActivityScheduleRequest.getDuration());
        portActivitiesSchedule.setLocation(portActivityScheduleRequest.getLocation());
        portActivitiesSchedule.setPrice(portActivityScheduleRequest.getPrice());

        try{
            SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("DD-MM-YY HH:mm:ss");
            Date data = DATE_FORMAT.parse(portActivityScheduleRequest.getStart_date_time());

            portActivitiesSchedule.setStart_date(data);
            portActivitiesSchedule.setStart_time(data);

            portActivitiesScheduleRepository.save(portActivitiesSchedule);
            return new ResponseEntity<>("Activity schedule added with success", HttpStatus.OK);
        }catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{port_activity_id}")
    public ResponseEntity<?> modifyPortActivitiesSchedule(@PathVariable Long port_activity_id, @Valid @RequestBody PortActivityScheduleRequest portActivityScheduleRequest) {

        List<PortActivitiesSchedule> portActivitiesSchedules = portActivitiesScheduleRepository.findByPortActivityId(port_activity_id);

        //este una singura
        if (!portActivitiesSchedules.isEmpty())
        {
            PortActivitiesSchedule portActivitiesSchedule = portActivitiesSchedules.get(0);

            portActivitiesSchedule.setDuration(portActivityScheduleRequest.getDuration());
            portActivitiesSchedule.setLocation(portActivityScheduleRequest.getLocation());
            portActivitiesSchedule.setPrice(portActivityScheduleRequest.getPrice());

            try{
                SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("DD-MM-YY HH:mm:ss");
                Date data = DATE_FORMAT.parse(portActivityScheduleRequest.getStart_date_time());

                portActivitiesSchedule.setStart_date(data);
                portActivitiesSchedule.setStart_time(data);

                portActivitiesScheduleRepository.save(portActivitiesSchedule);
                return new ResponseEntity<>("Activity schedule modified successfully", HttpStatus.OK);
            }catch(Exception e)
            {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
