package com.hostelconnect.service;

import com.hostelconnect.model.Role;
import com.hostelconnect.model.User;
import com.hostelconnect.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public User registerUser(User user) {

        // Encrypt password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Default role
        user.setRole(Role.STUDENT);

        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}