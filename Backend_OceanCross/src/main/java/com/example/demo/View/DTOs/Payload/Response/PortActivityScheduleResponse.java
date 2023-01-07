package com.example.demo.View.DTOs.Payload.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class PortActivityScheduleResponse {
    private Long id;

    private Long portActivityId;

    private String start_date_time;

    private Integer duration;

    private String location;

    double price;
}
