package com.example.demo.View.DTOs.Payload.Request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Getter
@Setter
public class SignupRequest {

    @NotBlank
    private String name;

    private String username;

    @NotBlank
    private String password;

    private String role_name;

}
