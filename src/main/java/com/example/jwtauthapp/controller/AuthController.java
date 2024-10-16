package com.example.jwtauthapp.controller;

import com.example.jwtauthapp.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/auth")
public class AuthController {
@Autowired
    JwtService service;
    @PostMapping("/login")
    public String login() {
        return "hi";
    }

    @PostMapping("/in")
    public String in() {
        return "hi";
    }

    @PreAuthorize("hasAnyAuthority('admin')")
    @GetMapping("/some")
    public String some(@AuthenticationPrincipal UserDetails principal) {
        System.out.println(principal.getUsername());
        String token= service.generateToken(principal);
        System.out.println(service.extractUserName(token));
        return token;
    }
}
