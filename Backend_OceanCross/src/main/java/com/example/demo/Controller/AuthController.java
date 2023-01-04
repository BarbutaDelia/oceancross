package com.example.demo.Controller;

import com.example.demo.Model.Entities.User;
import com.example.demo.Model.Repositories.RoleRepository;
import com.example.demo.Model.Repositories.UserRepository;
import com.example.demo.View.DTOs.Payload.Request.LoginRequest;
import com.example.demo.View.DTOs.Payload.Request.SignupRequest;
import com.example.demo.View.DTOs.Payload.Response.JwtResponse;
import com.example.demo.View.DTOs.Payload.Response.MessageResponse;
import com.example.demo.Security.JWT.JwtUtils;
import com.example.demo.Security.Services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String role = String.valueOf(userDetails.getAuthorities());

        //fiindca am nevoie sa creez rolurile ca lista de grantedAuthorities, role e de fapt o lista, si ii scot []
        role = role.substring(1, role.length() - 1);

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                role));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getName())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getName(),
                signUpRequest.getUsername(),
                encoder.encode(signUpRequest.getPassword()),
                roleRepository.findByName(signUpRequest.getRole_name()));

        System.out.println(user.toString());
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}

