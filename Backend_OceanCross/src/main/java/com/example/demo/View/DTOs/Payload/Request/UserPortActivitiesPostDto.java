package com.example.demo.View.DTOs.Payload.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserPortActivitiesPostDto
{
    Long userId;
    Long cruiseId;
    Long portActivityScheduleId;
}
