package com.hostelconnect.controller;

import com.hostelconnect.model.User;
import com.hostelconnect.model.Role;
import com.hostelconnect.repository.UserRepository;
import com.hostelconnect.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    // ✅ REGISTER
    @PostMapping("/register")
    public String register(@RequestBody User user) {

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return "User already exists!";
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if (user.getRole() == null) {
            user.setRole(Role.STUDENT); // default role
        }

        userRepository.save(user);

        return "User Registered Successfully";
    }

    // ✅ LOGIN
    @PostMapping("/login")
    public String login(@RequestBody User request) {

        Optional<User> optionalUser =
                userRepository.findByEmail(request.getEmail());

        if (optionalUser.isEmpty()) {
            return "User not found";
        }

        User user = optionalUser.get();

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {
            return "Invalid password";
        }

        return jwtUtil.generateToken(
                user.getEmail(),
                user.getRole().name()
        );
    }
}