package com.example.demo.Model.Services;

import com.example.demo.Model.Entities.PortActivities;
import com.example.demo.Model.Entities.PortActivitiesSchedule;
import com.example.demo.Model.Entities.UserPortActivities;
import com.example.demo.Model.Repositories.PortActivitiesRepository;
import com.example.demo.Model.Repositories.PortActivitiesScheduleRepository;
import com.example.demo.Model.Repositories.UserCruiseRepository;
import com.example.demo.Model.Repositories.UserPortActivitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public List<UserPortActivities> getCurrentUserActivities(List<Long> cuid)
    {
        List<UserPortActivities>   userPortActivities=new ArrayList<>();

        for(Long id:cuid)
        {
            userPortActivities.addAll(userPortActivitiesRepository.findByUserCruiseId(id));
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

    public List<PortActivitiesSchedule> getPortActivitySchedule(List<Long> ids)
    {
        List<PortActivitiesSchedule> portActivitiesSchedules=new ArrayList<>();
        for(Long id:ids)
        {
            portActivitiesSchedules.addAll(portActivitiesScheduleRepository.findByPortActivityId(id));
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
        userPortActivitiesRepository.save(ups);
    }
    public boolean checkIfExists(Long id)
    {
       if (portActivitiesScheduleRepository.findById(id).isEmpty())
       {
           return false;
       }
       return true;
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
