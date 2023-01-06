package com.example.demo.Model.Entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="user_activities")
public class UserPortActivities
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long id;

    @Column(name="port_activity_schedule_id")
    Long portActivityScheduleId;

    @Column(name="user_cruise_id")
    Long userCruiseId;
}
