package com.example.jwtauthapp.service;

import com.example.jwtauthapp.DTOs.CredentialsDTO;
import com.example.jwtauthapp.entity.Role;
import com.example.jwtauthapp.entity.User;
import com.example.jwtauthapp.repo.UserRepository;
import com.example.jwtauthapp.security.CustomUserDetailsService;
import com.example.jwtauthapp.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final CustomUserDetailsService userDetailsService;

    private final AuthenticationProvider authenticationManager;

    private final JwtService jwtService;

    private final BCryptPasswordEncoder passwordEncoder;

    public void register(CredentialsDTO dto) {
        if (userRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new BadCredentialsException("User already exists");
        }
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRoles(Set.of(new Role(2L, "user")));
        userRepository.save(user);
    }

    public String login(CredentialsDTO dto) {
        UserDetails user = userDetailsService.loadUserByUsername(dto.getUsername());
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.getUsername(),
                        dto.getPassword()
                )
        );
        return jwtService.generateToken(user);
    }


}
