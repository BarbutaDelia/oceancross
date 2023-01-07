package com.example.demo.View.DTOs.Payload.Request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PortActivityScheduleRequest {
    private String start_date_time;
    private Integer duration;
    private String location;
    private Double price;
}
