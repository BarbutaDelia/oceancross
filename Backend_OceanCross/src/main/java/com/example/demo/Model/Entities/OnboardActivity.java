package com.example.demo.Model.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "cruise_onboard_activities")
public class OnboardActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cruise_id")
    @JsonBackReference
    private Cruise cruise;

    private String name;

    @Temporal(TemporalType.DATE)
    Date start_date;

    @Temporal(TemporalType.TIME)
    Date start_time;

    private Integer duration;

    private String location;
}
