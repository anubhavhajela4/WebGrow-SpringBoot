package com.example.authentication.controller;

import lombok.Getter;
import lombok.Setter;

// DTO for holding the authentication request data
@Getter
@Setter
public class AuthRequest {
    private String username;
    private String password;

    // Getters and setters
}
