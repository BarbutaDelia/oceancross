package com.example.demo.View.DTOs;

import com.example.demo.Model.Entities.PortActivitiesSchedule;
import com.example.demo.Model.Entities.UserPortActivities;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserPortActivityDto
{
    Long userID;
    Long cruiseID;

    Long portID;
   List<PortActivitiesSchedule> allPortActivities;

   List<UserPortActivities> userPortActivities;
}
