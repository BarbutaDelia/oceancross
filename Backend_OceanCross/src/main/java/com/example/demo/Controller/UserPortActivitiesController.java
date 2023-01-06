package com.example.demo.Controller;

import com.example.demo.Model.Entities.PortActivities;
import com.example.demo.Model.Entities.PortActivitiesSchedule;
import com.example.demo.Model.Entities.UserCruises;
import com.example.demo.Model.Entities.UserPortActivities;
import com.example.demo.Model.Exceptions.PortActivities.PortActivityScheduleNotFound;
import com.example.demo.Model.Services.CruiseService;
import com.example.demo.Model.Services.UserPortActivitiesService;
import com.example.demo.Model.Services.WishlistService;
import com.example.demo.View.DTOs.Payload.Response.MessageResponse;
import com.example.demo.View.DTOs.UserPortActivityDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/port-activities")
public class UserPortActivitiesController
{
    @Autowired
    UserPortActivitiesService userPortActivitiesService;
    @Autowired
    WishlistService wishlistService;
    @GetMapping("/{user_id}/{port_id}/{cruise_id}")
    public ResponseEntity<?> getPortAndUserActivities(@PathVariable Long user_id,@PathVariable Long port_id,@PathVariable Long cruise_id)
    {


        List<Long> portActivitiesIds=userPortActivitiesService.getPortActivityByID(port_id);
        List<PortActivitiesSchedule> portActivitiesSchedules= userPortActivitiesService.getPortActivitySchedule(portActivitiesIds);
        List<Long>idsToSearchInUserActivities=userPortActivitiesService.getUserCruisesIds(user_id,cruise_id);
        List<UserPortActivities> currentUserActivities=userPortActivitiesService.getCurrentUserActivities(idsToSearchInUserActivities);
        UserPortActivityDto userPortActivityDto=new UserPortActivityDto(user_id,cruise_id,port_id,portActivitiesSchedules,currentUserActivities);
       return new ResponseEntity<>(userPortActivityDto,HttpStatus.OK);
    }

    @PostMapping("/{port_activity_schedule_id}/{user_id}/{cruise_id}")
    public ResponseEntity<?> addActivityToUser(@PathVariable Long port_activity_schedule_id,@PathVariable Long user_id, @PathVariable Long cruise_id )
    {
        try
        {UserPortActivities ups=new UserPortActivities();
          List<Long> id=wishlistService.getIdByUserIdAndCruiseId(user_id,cruise_id);
          if(id.isEmpty())
          {
              return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
          }
          ups.setUserCruiseId(id.get(0));

          ups.setPortActivityScheduleId(port_activity_schedule_id);
          if(!userPortActivitiesService.checkIfExists(port_activity_schedule_id))
              throw new PortActivityScheduleNotFound(user_id);
          userPortActivitiesService.addToUserActivity(ups);
          new ResponseEntity(HttpStatus.OK);
        }catch( PortActivityScheduleNotFound e)
        {
            return new ResponseEntity<>(e,HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

}
