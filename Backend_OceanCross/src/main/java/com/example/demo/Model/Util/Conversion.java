package com.example.demo.Model.Util;

import com.example.demo.Model.Entities.Cruise;
import com.example.demo.View.DTOs.CruiseDto;

import java.util.ArrayList;
import java.util.List;

public class Conversion {
    public static List<CruiseDto> getCruiseDtosFromCruises(List<Cruise> cruises){
        List<CruiseDto> cruisesDtos = new ArrayList<>();
        for(Cruise cruise: cruises){
            CruiseDto cruiseDto = new CruiseDto(cruise.getId(), cruise.getName(), cruise.getStart_date(),
                    cruise.getEnd_date(), cruise.getPrice(), cruise.getOnboardActivities(), cruise.getCruisePorts());
            cruisesDtos.add(cruiseDto);
        }
        return cruisesDtos;
    }

    public static CruiseDto getCruiseDtoFromCruise(Cruise cruise){
        return new CruiseDto(cruise.getId(), cruise.getName(), cruise.getStart_date(),
                cruise.getEnd_date(), cruise.getPrice(), cruise.getOnboardActivities(), cruise.getCruisePorts());
    }
}
