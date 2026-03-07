package com.hostelconnect.controller;

import com.hostelconnect.dtorequest.ComplaintRequest;
import com.hostelconnect.model.Complaint;
import com.hostelconnect.service.ComplaintService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {

    private final ComplaintService complaintService;

    @GetMapping("/dashboard")
    public String studentDashboard() {
        return "Welcome Student 🔥";
    }

    // ⭐ Student creates complaint
    @PostMapping("/complaints")
    public Complaint createComplaint(@RequestBody ComplaintRequest request) {
        return complaintService.createComplaint(request);
    }
}