package com.example.demo.Model.Util;

import com.example.demo.Model.Entities.*;
import com.example.demo.Model.Exceptions.Cruises.CruisePortNotFound;
import com.example.demo.Model.Services.CruiseService;
import com.example.demo.Model.Services.OnBoardActivityService;
import com.example.demo.Model.Services.PortService;
import com.example.demo.View.DTOs.CruiseDto;
import com.example.demo.View.DTOs.CruisePortDto;
import com.example.demo.View.DTOs.OnboardActivityDTO;
import com.example.demo.View.DTOs.Payload.Request.CruisePortRequest;
import com.example.demo.View.DTOs.Payload.Response.PortActivityScheduleResponse;
import com.example.demo.View.DTOs.UserPortActivityNewDto;

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
        return new CruisePortDto(cruisePort.getId(), cruisePort.getPort().getId(), cruisePort.getPort().getName(), cruisePort.getArrival_date(), cruisePort.getArrival_time(), cruisePort.getDuration());
    }

    public static List<CruisePortDto> getCruisePortDtosFrmCruisePorts(List<CruisePort> cruisePorts){
        List<CruisePortDto> cruisePortDtos = new ArrayList<>();
        for(CruisePort cruisePort: cruisePorts){
            CruisePortDto cruisePortDto = new CruisePortDto(cruisePort.getId(), cruisePort.getPort().getId(), cruisePort.getPort().getName(), cruisePort.getArrival_date(), cruisePort.getArrival_time(), cruisePort.getDuration());
            cruisePortDtos.add(cruisePortDto);
        }
        return cruisePortDtos;
    }

    public static List<OnboardActivity> getOnboardActivitiesFromOnboardActivityDTOs(List<OnboardActivityDTO> onboardActivityDTOS, Cruise cruise)
    {
        List<OnboardActivity> onboardActivities = new ArrayList<>();
        for (OnboardActivityDTO onboardActivityDto : onboardActivityDTOS) {
            OnboardActivity onboardActivity = new OnboardActivity();
            onboardActivity.setCruise(cruise);
            onboardActivity.setName(onboardActivityDto.getName());
            onboardActivity.setStart_date( onboardActivityDto.getStart_date());

            onboardActivity.setStart_time(onboardActivityDto.getStart_time());
            onboardActivity.setDuration(onboardActivityDto.getDuration());
            onboardActivity.setLocation(onboardActivityDto.getLocation());
            onboardActivities.add(onboardActivity);
        }
        return onboardActivities;
    }

    public static List<OnboardActivity> getOnboardActivitiesFromOnboardActivityDTOsPUT(List<OnboardActivityDTO> onboardActivityDTOS, Cruise cruise, OnBoardActivityService onBoardActivityService)
    {
        List<OnboardActivity> onboardActivities = new ArrayList<>();
        for (OnboardActivityDTO onboardActivityDto : onboardActivityDTOS) {
            OnboardActivity onboardActivity;
            try
            {
                onboardActivity =  onBoardActivityService.getActivity(onboardActivityDto.getId());
            }
            catch(RuntimeException e)
            {
                System.out.println(e.getMessage());
                onboardActivity = new OnboardActivity();
            }
            onboardActivity.setCruise(cruise);
            onboardActivity.setName(onboardActivityDto.getName());
            onboardActivity.setStart_date( onboardActivityDto.getStart_date());

            onboardActivity.setStart_time(onboardActivityDto.getStart_time());
            onboardActivity.setDuration(onboardActivityDto.getDuration());
            onboardActivity.setLocation(onboardActivityDto.getLocation());
            onboardActivities.add(onboardActivity);
        }
        return onboardActivities;
    }

    public static List<CruisePort> getCruisePortsFromCruisePortRequests(List<CruisePortRequest> cruisePortRequests, Cruise cruise, PortService portService)
    {
        List<CruisePort> cruisePorts = new ArrayList<>();
        try
        {
            for (CruisePortRequest cruisePortDto : cruisePortRequests) {
                CruisePort cruisePort = new CruisePort();
                Port port = portService.getPort(cruisePortDto.getPort_id());

                cruisePort.setPort(port);
                cruisePort.setCruise(cruise);
                cruisePort.setArrival_date(cruisePortDto.getArrival_date());
                cruisePort.setArrival_time(cruisePortDto.getArrival_time());
                cruisePort.setDuration(cruisePortDto.getDuration());
                cruisePorts.add(cruisePort);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            throw e;
        }

        return cruisePorts;
    }

    public static List<CruisePort> getCruisePortsFromCruisePortRequestsPUT(List<CruisePortRequest> cruisePortRequests, Cruise cruise, PortService portService, CruiseService cruiseService)
    {
        List<CruisePort> cruisePorts = new ArrayList<>();
        try
        {
            for (CruisePortRequest cruisePortDto : cruisePortRequests) {
                CruisePort cruisePort;
                try
                {
                    cruisePort =  cruiseService.getCruisePort(cruisePortDto.getId());
                }
                catch(CruisePortNotFound e)
                {
                    System.out.println(e.getMessage());
                    cruisePort = new CruisePort();
                }
                Port port = portService.getPort(cruisePortDto.getPort_id());

                cruisePort.setPort(port);
                cruisePort.setCruise(cruise);
                cruisePort.setArrival_date(cruisePortDto.getArrival_date());
                cruisePort.setArrival_time(cruisePortDto.getArrival_time());
                cruisePort.setDuration(cruisePortDto.getDuration());
                cruisePorts.add(cruisePort);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            throw e;
        }

        return cruisePorts;
    }

    public static List<PortActivityScheduleResponse> PortActivityScheduleTOPortActivityScheduleResponse(List<PortActivitiesSchedule> portActivitiesSchedule)
    {
        List<PortActivityScheduleResponse> portActivityScheduleResponses = new ArrayList<PortActivityScheduleResponse>();
        for (PortActivitiesSchedule pas:portActivitiesSchedule)
        {
            String start_date_time = pas.getStart_date() + " " + pas.getStart_time();
            portActivityScheduleResponses.add(new PortActivityScheduleResponse(pas.getId(), pas.getPortActivityId(), start_date_time, pas.getDuration(), pas.getLocation(), pas.getPrice()));
        }
        return portActivityScheduleResponses;
    }

    public static List<UserPortActivityNewDto> convertToActivitiesDto(List<PortActivitiesSchedule> pas,List<PortActivities>pa)
    {
        List<UserPortActivityNewDto> returnList=new ArrayList<>();
        Integer contor=pas.size();
        for(int i=0;i<contor;++i)
        {
            PortActivitiesSchedule tempPas=pas.get(i);
            PortActivities tempPa=pa.get(i);
            returnList.add(new UserPortActivityNewDto(tempPas.getId(),tempPa.getName(),tempPa.getImagePath(),tempPas.getPortActivityId(),tempPas.getStart_date(),tempPas.getStart_time(), tempPas.getDuration(), tempPas.getLocation(), tempPas.getPrice()));
        }
        return returnList;
    }
}
