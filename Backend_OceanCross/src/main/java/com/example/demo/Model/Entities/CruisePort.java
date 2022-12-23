package com.example.demo.Model.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "cruise_ports")
public class CruisePort {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @MapsId("id")
    @JoinColumn(name = "cruise_id")
    @JsonBackReference
    Cruise cruise;

    @ManyToOne
    @MapsId("id")
    @JoinColumn(name = "port_id")
    @JsonBackReference
    Port port;

    @Temporal(TemporalType.DATE)
    Date arrival_date;

    @Temporal(TemporalType.TIME)
    Date arrival_time;

    private Integer duration;


}
