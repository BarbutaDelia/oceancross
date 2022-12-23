package com.example.demo.Model.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "cruises")
public class Cruise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Temporal(TemporalType.DATE)
    Date start_date;

    @Temporal(TemporalType.DATE)
    Date end_date;

    private Double price;

    @OneToMany(mappedBy = "cruise", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<OnboardActivity> onboardActivities;

    @OneToMany(mappedBy = "cruise", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<CruisePort> cruisePorts;

}
