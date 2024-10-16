package com.example.jwtauthapp.security;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class JwtServiceTest {

    JwtService jwtService=new JwtService();
    CustomUserDetailsService service= Mockito.mock(CustomUserDetailsService.class);

    @Test
    void validateToken() {
        System.out.println(jwtService.isTokenValid("eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTcyOTA1MzE2OCwiZXhwIjoxNzI5MDU0NjA4fQ.BY3MlKz9XJ9Uj489592LTR-8AgDCZpoOOStpUByEWlk7NPawRhTFKQJdq0LeZ7bE",service.loadUserByUsername("admin")));
    }

    @Test
    void getSigningKey() {
        System.out.println(jwtService.secretKey);
        System.out.println(jwtService.secretKey);
        System.out.println(jwtService.secretKey);
        System.out.println(jwtService.secretKey);
    }
}