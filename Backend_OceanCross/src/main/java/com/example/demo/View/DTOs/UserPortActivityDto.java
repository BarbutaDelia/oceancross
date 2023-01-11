package com.example.demo.View.DTOs;

import com.example.demo.Model.Entities.PortActivities;
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
   List<UserPortActivityNewDto> userActivities;
   List<UserPortActivityNewDto> portActivities;
}
