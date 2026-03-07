package com.hostelconnect.dtorequest;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class RegisterRequest {

    @NotBlank
    private String name;

    @Email
    private String email;

    @NotBlank
    private String password;

    private String role;

    // getters & setters
}