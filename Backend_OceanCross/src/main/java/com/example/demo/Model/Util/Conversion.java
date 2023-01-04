package com.example.demo.Model.Util;

import com.example.demo.Model.Entities.Cruise;
import com.example.demo.Model.Entities.CruisePort;
import com.example.demo.View.DTOs.CruiseDto;
import com.example.demo.View.DTOs.CruisePortDto;

import java.util.ArrayList;
import java.util.List;

public class Conversion {
    public static List<CruiseDto> getCruiseDtosFromCruises(List<Cruise> cruises){
        List<CruiseDto> cruisesDtos = new ArrayList<>();
        for(Cruise cruise: cruises){
            CruiseDto cruiseDto = new CruiseDto(cruise.getId(), cruise.getName(), cruise.getStart_date(),
                    cruise.getEnd_date(), cruise.getPrice(), cruise.getOnboardActivities(), getCruisePortDtosFrmCruisePorts(cruise.getCruisePorts()));
            cruisesDtos.add(cruiseDto);
        }
        return cruisesDtos;
    }

    public static CruiseDto getCruiseDtoFromCruise(Cruise cruise){
        return new CruiseDto(cruise.getId(), cruise.getName(), cruise.getStart_date(),
                cruise.getEnd_date(), cruise.getPrice(), cruise.getOnboardActivities(), getCruisePortDtosFrmCruisePorts(cruise.getCruisePorts()));
    }

    public static CruisePortDto getCruisePortDtoFromCruisePort(CruisePort cruisePort){
        return new CruisePortDto(cruisePort.getId(), cruisePort.getPort().getName(), cruisePort.getArrival_date(), cruisePort.getArrival_time(), cruisePort.getDuration());
    }

    public static List<CruisePortDto> getCruisePortDtosFrmCruisePorts(List<CruisePort> cruisePorts){
        List<CruisePortDto> cruisePortDtos = new ArrayList<>();
        for(CruisePort cruisePort: cruisePorts){
            CruisePortDto cruisePortDto = new CruisePortDto(cruisePort.getId(), cruisePort.getPort().getName(), cruisePort.getArrival_date(), cruisePort.getArrival_time(), cruisePort.getDuration());
            cruisePortDtos.add(cruisePortDto);
        }
        return cruisePortDtos;
    }
}
