package com.hostelconnect.controller;

import com.hostelconnect.model.Role;
import com.hostelconnect.model.User;
import com.hostelconnect.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // ✅ Register Student
    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    // ✅ Create Admin (Temporary - Later secure this)
    @PostMapping("/create-admin")
    public User createAdmin(@RequestBody User user) {
        user.setRole(Role.ADMIN);
        return userService.registerUser(user);
    }

    // ✅ Get All Users (Temporary - Later secure this)
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}