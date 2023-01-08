package com.example.demo.Controller;

import com.example.demo.Model.Entities.PortActivities;
import com.example.demo.Model.Entities.PortActivitiesSchedule;
import com.example.demo.Model.Entities.UserCruises;
import com.example.demo.Model.Entities.UserPortActivities;
import com.example.demo.Model.Exceptions.PortActivities.ActivityAlreadyExists;
import com.example.demo.Model.Exceptions.PortActivities.PortActivityScheduleNotFound;
import com.example.demo.Model.Exceptions.PortActivities.UserPortActivityNotFound;
import com.example.demo.Model.Exceptions.Wishlist.UserCruisesNotFound;
import com.example.demo.Model.Services.CruiseService;
import com.example.demo.Model.Services.UserPortActivitiesService;
import com.example.demo.Model.Services.WishlistService;
import com.example.demo.View.DTOs.Payload.Request.UserPortActivitiesPostDto;
import com.example.demo.View.DTOs.Payload.Request.UserPortActivitiesPostDtoList;
import com.example.demo.View.DTOs.Payload.Response.MessageResponse;
import com.example.demo.View.DTOs.UserPortActivityDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/port-activities")
public class UserPortActivitiesController
{
    @Autowired
    UserPortActivitiesService userPortActivitiesService;
    @Autowired
    WishlistService wishlistService;
    @GetMapping("/{user_id}/{port_id}/{cruise_id}")
    public ResponseEntity<?> getPortAndUserActivities(@PathVariable Long user_id,@PathVariable Long port_id,@PathVariable Long cruise_id)
    {

        // ar trebui sa returnez doua liste, una pentru activitatile din port si una pentru activitatile userului de pe croaziera.


        //luam id urile activitatile din portul curent
        List<Long> portActivitiesIds=userPortActivitiesService.getPortActivityByID(port_id);
        //luam activitatile programate


        List<PortActivitiesSchedule> portActivitiesSchedules= userPortActivitiesService.getPortActivitySchedule(portActivitiesIds);
        // le filtram sa aiba data mai mare sau egala cu data de incepere a stationarii.
        portActivitiesSchedules=userPortActivitiesService.filteredByDate(portActivitiesSchedules,cruise_id,port_id);

        //luam id urile din userCruises corespunzatoare user ului curent si cruise ului curent
        List<Long>idsToSearchInUserActivities=userPortActivitiesService.getUserCruisesIds(user_id,cruise_id);
        // apoi ar trebui defapt sa cautam dupa userCruiseId portActivitiesSchedule
        List<UserPortActivities> currentUserActivities=userPortActivitiesService.getCurrentUserActivities(idsToSearchInUserActivities);

        List <Long> userActivities=new ArrayList<>();
        for (UserPortActivities u :currentUserActivities)
        {
            userActivities.add(u.getPortActivityScheduleId());
        }
        //iau activitatile userului curent de pe croaziera curenta din portul curent :)
        List <PortActivitiesSchedule> userPortActivities=userPortActivitiesService.getPortActivityScheduleById(userActivities);


        UserPortActivityDto userPortActivityDto=new UserPortActivityDto(user_id,cruise_id,port_id,portActivitiesSchedules,userPortActivities);

        return new ResponseEntity<>(userPortActivityDto,HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<?> addActivityToUser( @RequestBody UserPortActivitiesPostDtoList p )
    {
        for(UserPortActivitiesPostDto param:p.getUserPortActivitiesPostDtoList())
        {
            // am nevoie ca userul curent sa aiba croaziera in portul curent si in PortActivitiesSchedule sa fie inregistrata
            //  activitatea pe care vreau sa o adaug.
            try {
                UserPortActivities ups = new UserPortActivities();

                // iau id ul inregistrat in wish list pentru user ul curent si croaziera curenta,daca nu Exista in wishlist croaziera pt user se returneaza
                // un cod de eroare

                List<Long> id = wishlistService.getIdByUserIdAndCruiseId(param.getUserId(), param.getCruiseId());
                if (id.isEmpty())
                {
                    return new ResponseEntity(HttpStatus.FAILED_DEPENDENCY);
                }
                ups.setUserCruiseId(id.get(0));
                // daca id ul activitatii programate in port nu exista in PortActivtySchedule se arunca o eroare
                ups.setPortActivityScheduleId(param.getPortActivityScheduleId());
                if (!userPortActivitiesService.checkIfExistsInPortActivitySchedule(param.getPortActivityScheduleId()))
                    throw new PortActivityScheduleNotFound(param.getUserId());
                try
                {
                    userPortActivitiesService.addToUserActivity(ups);

                } catch (ActivityAlreadyExists e)
                {
                    return new ResponseEntity(e, HttpStatus.CONFLICT);
                }
            }
            catch (PortActivityScheduleNotFound e)
            {
                return new ResponseEntity<>(e, HttpStatus.FAILED_DEPENDENCY);
            }
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{port_activity_schedule_id}/{user_id}/{cruise_id}")
    public ResponseEntity<?> deleteActivityFromUser(@PathVariable Long port_activity_schedule_id, @PathVariable Long user_id,@PathVariable Long cruise_id)
    {
        try
        {
            List<Long> userCruiseId =wishlistService.getIdByUserIdAndCruiseId(user_id,cruise_id);
            if(!userCruiseId.isEmpty()) {
                userPortActivitiesService.deleteByActivityScheduleAndCruiseId(port_activity_schedule_id, userCruiseId.get(0));
                return new ResponseEntity<>(HttpStatus.OK);
            }
            else throw new UserCruisesNotFound(user_id);

        }
        catch (UserCruisesNotFound e)
        {
            return new ResponseEntity<>(e,HttpStatus.NOT_FOUND);
        }
        catch (UserPortActivityNotFound e)
        {
            return new ResponseEntity<>(e,HttpStatus.NOT_FOUND);
        }

    }
}
