package com.example.demo.Model.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "port_activities_schedule")
public class PortActivitiesSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "port_activity_id")
    Long portActivityId;


    @Temporal(TemporalType.DATE)
    Date start_date;

    @Temporal(TemporalType.TIME)
    Date start_time;

    private Integer duration;

    private String location;

    double price;
}
