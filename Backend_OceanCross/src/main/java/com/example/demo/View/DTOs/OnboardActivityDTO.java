package com.example.demo.View.DTOs;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.sql.Date;
import java.sql.Time;

@Getter
@Setter
public class OnboardActivityDTO {
    //TODO: de vorbit daca primesc si FK sau doar datele(eu zic ca si FK)

    private String name;
    @Temporal(TemporalType.DATE)
    private Date start_date;
    @Temporal(TemporalType.TIME)
    private Date start_time;

    private Integer duration;

    private String location;

}
