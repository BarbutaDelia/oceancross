package com.example.demo.View.DTOs;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.sql.Time;

@Getter
@Setter
public class OnboardActivityDTO {
    private Long id;

    private String name;
    @Temporal(TemporalType.DATE)
    private Date start_date;
    private Time start_time;

    private Integer duration;

    private String location;

}
