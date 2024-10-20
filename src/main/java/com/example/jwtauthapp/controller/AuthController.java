package com.example.jwtauthapp.controller;

import com.example.jwtauthapp.DTOs.CredentialsDTO;
import com.example.jwtauthapp.service.AuthService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {


    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody CredentialsDTO dto) {
        System.out.println(dto);
        String token = authService.login(dto);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody CredentialsDTO dto) {

        System.out.println(dto.toString());
        authService.register(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Successfully registered");
    }

    @GetMapping("/secured")
    public ResponseEntity<String> secured() {

        return ResponseEntity.ok("passed sec check");
    }


}
