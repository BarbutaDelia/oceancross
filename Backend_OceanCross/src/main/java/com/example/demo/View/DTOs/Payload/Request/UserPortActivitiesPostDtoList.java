package com.example.demo.View.DTOs.Payload.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserPortActivitiesPostDtoList
{
    List<UserPortActivitiesPostDto> userPortActivitiesPostDtoList;
}
