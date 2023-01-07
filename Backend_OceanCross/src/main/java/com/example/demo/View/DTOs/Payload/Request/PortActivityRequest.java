package com.example.demo.View.DTOs.Payload.Request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class PortActivityRequest {
    private String name;
    private String image_path;
}
