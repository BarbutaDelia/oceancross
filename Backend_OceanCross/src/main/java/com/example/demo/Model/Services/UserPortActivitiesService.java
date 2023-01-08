package com.example.demo.Model.Services;

import com.example.demo.Model.Entities.*;
import com.example.demo.Model.Exceptions.PortActivities.ActivityAlreadyExists;
import com.example.demo.Model.Exceptions.PortActivities.UserPortActivityNotFound;
import com.example.demo.Model.Repositories.PortActivitiesRepository;
import com.example.demo.Model.Repositories.PortActivitiesScheduleRepository;
import com.example.demo.Model.Repositories.UserCruiseRepository;
import com.example.demo.Model.Repositories.UserPortActivitiesRepository;
import com.example.demo.Security.Services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserPortActivitiesService
{

    @Autowired
    UserPortActivitiesRepository userPortActivitiesRepository;
    @Autowired
    PortActivitiesScheduleRepository portActivitiesScheduleRepository;
    @Autowired
    UserCruiseRepository userCruiseRepository;
    @Autowired
    PortActivitiesRepository portActivitiesRepository;
    @Autowired
    CruiseService cruiseService;

    public List<UserPortActivities> getCurrentUserActivities(List<Long> cuid)
    {
        List<UserPortActivities>   userPortActivities=new ArrayList<>();

        for(Long id:cuid)
        {
            List<UserPortActivities> tempUpa=userPortActivitiesRepository.findByUserCruiseId(id);
            for (UserPortActivities u:tempUpa
            )
            {
                userPortActivities.add(u);
            }

        }

        return userPortActivities;
    }

    public List<Long> getPortActivityByID(Long pid)
    {

        List<PortActivities> currentPortActivities = portActivitiesRepository.findByPortId(pid);
        List<Long> ids=new ArrayList<>();
        for(PortActivities p:currentPortActivities)
        {
            ids.add(p.getId());
        }
        return ids;

    }




    public List<PortActivitiesSchedule> getPortActivityScheduleById(List<Long>ids)
    {

        List<PortActivitiesSchedule> p = portActivitiesScheduleRepository.findAllById(ids);
        return  p;
    }

    public List<PortActivitiesSchedule> getPortActivitySchedule(List<Long> ids)
    {
        List<PortActivitiesSchedule> portActivitiesSchedules=new ArrayList<>();
        for(Long id:ids)
        {
            List<PortActivitiesSchedule> tempPas=portActivitiesScheduleRepository.findByPortActivityId(id);
            for (PortActivitiesSchedule p:tempPas)
            {
                portActivitiesSchedules.add(p);
            }

        }
        return portActivitiesSchedules;
    }
    public List<Long> getUserCruisesIds(Long uid,Long cid)
    {
        // returnez id urile croazierelor userului
        List<Long> userCruisesIds= userCruiseRepository.findIdByUserIdAndCruiseId(uid,cid);
        return userCruisesIds;
    }

    public void addToUserActivity(UserPortActivities ups)
    {
        if(userPortActivitiesRepository.findByUserCruiseIdAndPortActivityScheduleId(ups.getUserCruiseId(),ups.getPortActivityScheduleId()).isEmpty()) {
            userPortActivitiesRepository.save(ups);
        }
    }
    public boolean checkIfExistsInPortActivitySchedule(Long id)
    {
        if (portActivitiesScheduleRepository.findById(id).isEmpty())
        {
            return false;
        }
        return true;
    }
    public List<PortActivitiesSchedule> filteredByDate(List<PortActivitiesSchedule> userPortActivities,Long cruise_id,Long port_id)
    {
        //am luat cruise ul cu id ul cruise ului curent
        Cruise cruise = cruiseService.getCruise(cruise_id);
        // am luat porturile din cruise ul curent
        List <CruisePort> ports=cruise.getCruisePorts();
        // Caut portul cu id ul portului curent
        CruisePort portCurent;

        List<PortActivitiesSchedule> filtredPortActivities=new ArrayList<>();
        for (CruisePort c:ports)
        {
            if (c.getPort().getId()==port_id)
            {
                portCurent = c;
                for (PortActivitiesSchedule pas:userPortActivities)
                {
                    if(pas.getStart_date().getTime()>=portCurent.getArrival_date().getTime())
                        filtredPortActivities.add(pas);
                }
            }
        }

        return filtredPortActivities;
    }

    public void deleteByActivityScheduleAndCruiseId(Long activityScheduleId,Long cruiseUserId)
    {

        List<UserPortActivities> upa= userPortActivitiesRepository.findByUserCruiseIdAndPortActivityScheduleId(cruiseUserId,activityScheduleId);
        if(!upa.isEmpty())
        {
            for (UserPortActivities ua:upa)
            {
                userPortActivitiesRepository.deleteById(ua.getId());

            }

        }else throw new UserPortActivityNotFound();
    }
    public void forTest()
    {
        PortActivities portActivities=new PortActivities();
        portActivities.setImagePath("path1");
        long id=1;
        portActivities.setPortId(id);
        portActivities.setUserId(id);
        portActivities.setName("Ceva");

        portActivitiesRepository.save(portActivities);

    }
}
