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
public class CruisePortDto {
    private Long id;

    private Long port_id;
    private String name;
    private Date arrival_date;
    private Date arrival_time;
    private Integer duration;
}
