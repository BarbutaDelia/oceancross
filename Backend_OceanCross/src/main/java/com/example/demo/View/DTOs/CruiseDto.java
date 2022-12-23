package com.example.demo.View.DTOs;

import com.example.demo.Model.Entities.CruisePort;
import com.example.demo.Model.Entities.OnboardActivity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CruiseDto {
    private Long id;
    private String name;
    private Date start_date;
    private Date end_date;
    private Double price;
    private List<OnboardActivity> onboardActivities;
    private List<CruisePort> cruisePorts;
}
