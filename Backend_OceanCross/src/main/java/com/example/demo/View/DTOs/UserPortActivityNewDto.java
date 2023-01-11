package com.example.demo.View.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserPortActivityNewDto
{

    Long id;
    String name;
    String image;
    Long portActivityId;
    Date start_date;
    Date start_time;
    Integer duration;
    String location;
    Double price;
}
