package com.shaziya.jwtapp.controller;

import com.shaziya.jwtapp.dto.JwtResponse;
import com.shaziya.jwtapp.dto.UserAuthRequest;
import com.shaziya.jwtapp.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    
    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private UserDetailsService userDetailsService;
    
    @GetMapping(path = "/hello")
    public String sayHello() {
        return "Hello from home!";
    }
    
    @PostMapping(path = "/generate-token")
    public ResponseEntity<JwtResponse> generateToken(@RequestBody UserAuthRequest userAuthRequest) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(userAuthRequest.getUsername(), userAuthRequest.getPassword()));
        String token = jwtUtil.generateToken(userDetailsService.loadUserByUsername(userAuthRequest.getUsername()));
        return new ResponseEntity<>(new JwtResponse(token), HttpStatus.OK);
    }
}
