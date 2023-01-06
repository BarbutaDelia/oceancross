package com.example.demo.View.DTOs.Payload.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CruisePortRequest {
    private Long id; // id-ul portului
    private Date arrival_date;
    private Date arrival_time;
    private Integer duration;
}
