package com.example.authentication.controller;

import com.example.authentication.security.jwt.JwtUtil;
import com.example.authentication.appuser.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AppUserService appUserService;

    @PostMapping("/authenticate")
    public String authenticate(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            // Authenticate using the provided credentials
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        } catch (AuthenticationException e) {
            throw new Exception("Invalid username or password", e);
        }

        // Load user details if authentication succeeds
        final UserDetails userDetails = appUserService.loadUserByUsername(authRequest.getUsername());

        // Generate a JWT token using the username
        return jwtUtil.generateToken(userDetails.getUsername());
    }
}

