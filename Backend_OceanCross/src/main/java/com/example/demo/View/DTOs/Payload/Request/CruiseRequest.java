package com.example.demo.View.DTOs.Payload.Request;
import com.example.demo.View.DTOs.OnboardActivityDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.example.demo.Model.Entities.OnboardActivity;
import com.example.demo.View.DTOs.CruisePortDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@Getter
@Setter

public class CruiseRequest {
    @NotBlank
    private String name;
    @NotBlank @Temporal(TemporalType.DATE)
    private Date start_date;
    @NotBlank @Temporal(TemporalType.DATE)
    private Date end_date;
    @NotBlank
    private Double price;
    @NotBlank
    @JsonProperty("onboardActivities")
    private List<OnboardActivityDTO> onboardActivities;
    private List<CruisePortRequest> cruisePorts;
}
