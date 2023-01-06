package com.example.demo.View.DTOs.Payload.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CruisePortRequest {
    private Long id;
    private Long port_id;
    private Date arrival_date;
    private Time arrival_time;
    private Integer duration;
}
