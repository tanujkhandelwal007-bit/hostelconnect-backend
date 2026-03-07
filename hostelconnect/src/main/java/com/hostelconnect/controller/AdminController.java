package com.hostelconnect.controller;

import com.hostelconnect.model.Complaint;
import com.hostelconnect.model.ComplaintStatus;
import com.hostelconnect.model.Priority;
import com.hostelconnect.dtoresponse.ComplaintAnalyticsResponse;
import com.hostelconnect.service.ComplaintService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final ComplaintService complaintService;

    // ⭐ Complaint Analytics
    @GetMapping("/analytics")
    public ComplaintAnalyticsResponse getComplaintAnalytics() {
        return complaintService.getAnalytics();
    }

    // ⭐ Smart Complaint Search
    @GetMapping("/complaints/search")
    public List<Complaint> searchComplaints(

            @RequestParam(required = false) ComplaintStatus status,
            @RequestParam(required = false) Priority priority,
            @RequestParam(required = false) String roomNumber,
            @RequestParam(required = false) String studentEmail) {

        return complaintService.searchComplaints(
                status,
                priority,
                roomNumber,
                studentEmail
        );
    }
}